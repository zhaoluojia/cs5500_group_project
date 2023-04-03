import React, {useEffect, useState} from 'react'
import UserService from "../services/UserService";

const TestComponent = () => {

  const [users, setUsers] = useState([])
  const [userName, setUserName] = useState('')
  const [exercises, setExercises] = useState([])

  useEffect(() => {
    getAllUser()
  }, [])

  const getAllUser = () => {

    UserService.getAllUser().then((response) => {
      setUsers(response.data)
      console.log(response.data);
    });
  };

  const getAllExerciseByUserName = () => {

    UserService.getAllExerciseByUserName(userName).then((response) => {
      setExercises(response.data)
      console.log(response.data);
    });
  };

  return (
      <div>
        <h1>User List</h1>
        <table className="table table-hover">
          <thead>
          <tr>
            <th scope="col">User Id</th>
            <th scope="col">Username</th>
            <th scope="col">Weight</th>
          </tr>
          </thead>
          <tbody>
          {
            users.map(
                user =>
                    <tr key = {user.id}>
                      <th scope="row"> {user.id} </th>
                      <td> {user.userName}</td>
                      <td> {user.weight}</td>
                    </tr>
            )
          }
          </tbody>
        </table>
        <h1>Exercise List</h1>
        <div className="form-group">
          <label htmlFor="userName"
                 className="form-label mt-4">UserName</label>
          <input type="text" className="form-control"
                 id="userName" placeholder="UserName" value={userName}
                 onChange={(e) => setUserName(e.target.value)}/>
          <button
              type="submit" className="btn btn-primary"
              onClick={getAllExerciseByUserName}>Show Exercise List
          </button>
        </div>
        <table className="table table-hover">
          <thead>
          <tr>
            <th scope="col">Exercise Name</th>
            <th scope="col">Date</th>
            <th scope="col">Duration</th>
            <th scope="col">Calories</th>
          </tr>
          </thead>
          <tbody>
          {
            exercises.map(
                exercise =>
                    <tr key = {exercise.id}>
                      <th scope="row"> {exercise.exerciseName} </th>
                      <td> {exercise.date}</td>
                      <td> {exercise.duration}</td>
                      <td> {exercise.calories}</td>
                    </tr>
            )
          }
          </tbody>
        </table>
      </div>
  )
};

export default TestComponent;