import React, {useState} from 'react'
import UserService from "../services/UserService";
import HeaderComponent from "./HeaderComponent";

const AddExerciseComponent = () => {

  const [error, setError] = useState(null)
  const [userName, setUserName] = useState('')
  const [userExist, setUserExist] = useState(false)
  const [userId, setUserId] = useState('')
  const [date, setDate] = useState('');
  const [duration, setDuration] = useState('');
  const [exerciseName, setExerciseName] = useState('');
  const [weight, setWeight] = useState('');
  const [caloriesBurned, setCaloriesBurned] = useState('');
  const [successAddMessage, setSuccessAddMessage] = useState(false);

  const handleSubmitBtn = async (e) => {
    e.preventDefault();
    if (userName === '') {
      setError('Please enter username')
      return;
    }
    setError(null)
    try {
      const response1 = await UserService.getUserIdByUserName(userName);
      setUserId(response1.data);
      console.log('UserId:' + response1.data);
      const response2 = await UserService.getWeightByUserName(userName);
      setWeight(response2.data);
      console.log('Weight:' + response2.data);
      setUserExist(true);
    } catch (error) {
      setError('Username does not exit')
    }
  };

  const handleAddExerciseBtn = async (e) => {
    e.preventDefault();
    if (date === '') {
      setError('Please select a date');
      return
    }
    if (duration === '' || isNaN(parseFloat(duration))) {
      setError('Please enter a valid duration. (Cannot be empty, and enter numbers only.)');
      return
    }
    if (exerciseName === '') {
      setError('Please select an exercise type');
      return
    }
    setError(null)
    try {
      const response3 = await UserService.calculateCalories(weight, exerciseName, duration);
      setCaloriesBurned(response3.data);
      console.log('Calories burned:' + response3.data);
      const response4 = await UserService.createExercise(userId, exerciseName, date, duration);
      console.log(response4.data)
      setSuccessAddMessage(true);
    } catch (error) {
      setError('Failed to add exercise based on your input. Please try again.');
    }
  };

  return (
      <div>
        {<HeaderComponent/>}
        <h2 className="mt-4">Add Exercise</h2>
        {
            error &&
            <div className="alert alert-dismissible alert-danger mt-3 mb-0">
              {error}
            </div>
        }
        <div className="form-group">
          <label htmlFor="userName1"
                 className="form-label mt-4">UserName</label>
          <input type="text" className="form-control"
                 id="userName1" placeholder="UserName" value={userName}
                 onChange={(e) => setUserName(e.target.value)}/>
        </div>
        <button
            type="submit" className="btn btn-primary mt-4"
            onClick={handleSubmitBtn}>Submit
        </button>
        {
            userExist &&
            <div>
              <div className="form-group">
                <label htmlFor="exerciseDate"
                       className="form-label mt-2">Date</label>
                <input type="date" className="form-control"
                       id="exerciseDate"
                       value={date}
                       onChange={(e) => setDate(
                           e.target.value)}/>
              </div>
              <div className="form-group">
                <label htmlFor="exerciseDuration"
                       className="form-label mt-2">Duration</label>
                <input type="text" className="form-control"
                       id="exerciseDuration" placeholder="Duration"
                       value={duration}
                       onChange={(e) => setDuration(e.target.value)}/>
              </div>
              <div className="form-group">
                <label htmlFor="exercise"
                       className="form-label mt-2">Exercise Type</label>
                <div>
                  <select
                      id="exercise"
                      value={exerciseName}
                      onChange={(e) => setExerciseName(e.target.value)}
                      className="form-control"
                  >
                    <option value="">--Please select --</option>
                    <option value="running">Running</option>
                    <option value="kayaking">Kayaking</option>
                    <option value="cycling">Cycling</option>
                    <option value="walking">Walking</option>
                  </select>
                </div>
              </div>
              <button
                  type="submit" className="btn btn-primary mt-4"
                  onClick={handleAddExerciseBtn}>Add Exercise
              </button>
              {
                  successAddMessage &&
                  <div className="alert alert-dismissible alert-primary mt-4">
                    <button type="button" className="btn-close"
                            data-bs-dismiss="alert"></button>
                    <h4 className="alert-heading">Exercise added successfully!</h4>
                    <p className="mb-0"><strong>{caloriesBurned.toFixed(2)}</strong> calories are burned during
                      the exercise.</p>
                  </div>
              }
            </div>
        }
      </div>
  );
};

export default AddExerciseComponent;