import React, {useState} from 'react'
import UserService from "../services/UserService";

const PageTwoComponent = () => {

  const [error, setError] = useState(null)
  const [message, setMessage] = useState(null)
  const [userName, setUserName] = useState('')
  const [userExist, setUserExist] = useState(false)
  const [userId, setUserId] = useState('')

  const [calories, setCalories] = useState('')
  const [caloriesGoalStartDate, setCaloriesGoalStartDate] = useState('')
  const [caloriesGoalEndDate, setCaloriesGoalEndDate] = useState('')
  const [showCaloriesGoalForm, setShowCaloriesGoalForm] = useState(true);
  const [caloriesGoal, setCaloriesGoal] = useState(null)
  const [caloriesTotal, setCaloriesTotal] = useState('')
  const [showCaloriesProgress, setShowCaloriesProgress] = useState(false);

  const [duration, setDuration] = useState('')
  const [durationGoalStartDate, setDurationGoalStartDate] = useState('')
  const [durationGoalEndDate, setDurationGoalEndDate] = useState('')
  const [showDurationGoalForm, setShowDurationGoalForm] = useState(false);
  const [durationGoal, setDurationGoal] = useState(null)
  const [durationTotal, setDurationTotal] = useState('')
  const [showDurationProgress, setShowDurationProgress] = useState(false);

  const handleSubmitBtn = async (e) => {
    e.preventDefault();
    if (userName === '') {
      setError('Please enter username')
      return
    }
    setError(null)
    try {
      const response = await UserService.getUserIdByUserName(userName);
      setUserId(response.data);
      setUserExist(true);
      console.log(response.data);
    } catch (error) {
      setError('Username does not exit')
    }
  };

  const handelSetCaloriesGoalBtn = async (e) => {
    e.preventDefault();
    if (calories === '') {
      setError('Please enter your calories goal')
      return
    }
    if (caloriesGoalStartDate === '') {
      setError('Please select a start date')
      return
    }
    if (caloriesGoalEndDate === '') {
      setError('Please select an end date')
      return
    }
    if (caloriesGoalStartDate > caloriesGoalEndDate) {
      setError('The end date cannot be before the start date')
      return
    }
    setError(null)
    try {
      const response1 = await UserService.updateCaloriesGoal(userId, caloriesGoalStartDate, caloriesGoalEndDate, calories);
      console.log(response1.data);
      const response2 = await UserService.getCaloriesGoalByUserId(userId);
      setCaloriesGoal(response2.data);
      setMessage('You\'ve successfully set a goal!');
      console.log(response2.data);
      return;
    } catch (error) {
      setError('An error occurred. Please try again.');
    }
  };

  const handelSetDurationGoalBtn = async (e) => {
    e.preventDefault();
    if (duration === '') {
      setError('Please enter your duration goal')
      return
    }
    if (durationGoalStartDate === '') {
      setError('Please select a start date')
      return
    }
    if (durationGoalEndDate === '') {
      setError('Please select an end date')
      return
    }
    if (durationGoalStartDate > durationGoalEndDate) {
      setError('The end date cannot be before the start date')
      return
    }
    setError(null)
    try {
      const response1 = await UserService.updateDurationGoal(userId, durationGoalStartDate, durationGoalEndDate, duration);
      console.log(response1.data);
      const response2 = await UserService.getDurationGoalByUserId(userId);
      setDurationGoal(response2.data);
      setMessage('You\'ve successfully set a goal!');
      console.log(response2.data);
      return;
    } catch (error) {
      setError('An error occurred. Please try again.');
    }
  };

  const handleTrackCaloriesProgressBtn = async (e) => {
    try {
      const response = await UserService.getCaloriesTotalBetweenDates(userId, caloriesGoalStartDate, caloriesGoalEndDate);
      setCaloriesTotal(response.data);
      console.log(response.data);
      setShowCaloriesProgress(true);
    } catch (error) {
      setError('An error occurred. Please try again.');
    }
  };

  const handleTrackDurationProgressBtn = async (e) => {
    try {
      const response = await UserService.getDurationTotalBetweenDates(userId, durationGoalStartDate, durationGoalEndDate);
      setDurationTotal(response.data);
      console.log(response.data);
      setShowDurationProgress(true);
    } catch (error) {
      setError('An error occurred. Please try again.');
    }
  };

  return (
      <div className="mt-4">
        <h2>Set a Goal and Track your progress</h2>
        {
            error &&
            <div className="alert alert-dismissible alert-danger mt-3 mb-0">
              {error}
            </div>
        }
        {
            !error && message &&
            <div className="alert alert-dismissible alert-secondary mt-3 mb-0">
              {message}
            </div>
        }
        <div className="form-group">
          <label htmlFor="userName"
                 className="form-label mt-4">UserName</label>
          <input type="text" className="form-control"
                 id="userName" placeholder="UserName" value={userName}
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
                <label htmlFor="goal"
                       className="form-label mt-4">Select a Goal</label>
                <div className="form-check">
                  <input className="form-check-input" type="radio"
                         name="optionsRadios" id="caloriesGoalRadio"
                         value="caloriesGoalRadio"
                         defaultChecked
                         onChange={(e) => {
                           setShowCaloriesGoalForm(!showCaloriesGoalForm);
                           setShowDurationGoalForm(!showDurationGoalForm)
                         }}/>
                  <label className="form-check-label"
                         htmlFor="caloriesGoalRadio">
                    Calories Goal
                  </label>
                </div>
                <div className="form-check">
                  <input className="form-check-input" type="radio"
                         name="optionsRadios" id="durationGoalRadio"
                         value="durationGoalRadio"
                         onChange={(e) => {
                           setShowDurationGoalForm(!showDurationGoalForm);
                           setShowCaloriesGoalForm(!showCaloriesGoalForm)
                         }}/>
                  <label className="form-check-label"
                         htmlFor="durationGoalRadio">
                    Duration Goal
                  </label>
                </div>
              </div>
              {
                  showCaloriesGoalForm &&
                  <div>
                    <div className="form-group">
                      <label htmlFor="calories"
                             className="form-label mt-4">Calories Goal</label>
                      <input type="text" className="form-control"
                             id="calories" placeholder="Calories Goal"
                             value={calories}
                             onChange={(e) => setCalories(e.target.value)}/>
                    </div>
                    <div className="form-group">
                      <label htmlFor="caloriesGoalStartDate"
                             className="form-label mt-2 me-2">Start Date</label>
                      <input type="date" className="form-control"
                             id="caloriesGoalStartDate"
                             value={caloriesGoalStartDate}
                             onChange={(e) => setCaloriesGoalStartDate(
                                 e.target.value)}/>
                    </div>
                    <div className="form-group">
                      <label htmlFor="caloriesGoalEndDate"
                             className="form-label mt-2 me-2">End Date</label>
                      <input type="date" className="form-control"
                             id="caloriesGoalEndDate"
                             value={caloriesGoalEndDate}
                             onChange={(e) => setCaloriesGoalEndDate(
                                 e.target.value)}/>
                    </div>
                    <button
                        type="submit" className="btn btn-primary mt-4"
                        onClick={handelSetCaloriesGoalBtn}>Set Goal
                    </button>
                    {
                      caloriesGoal &&
                        <div>
                          <button
                              type="submit" className="btn btn-primary mt-4 mb-4"
                              onClick={handleTrackCaloriesProgressBtn}>Track My
                            Progress
                          </button>
                          {
                            showCaloriesProgress &&
                              <div>
                                <h5>You have completed {Math.round(
                                caloriesTotal / calories * 100)}%
                                of your calories goal!</h5>
                                <div className="progress mb-4">
                                  <div
                                  className="progress-bar progress-bar-striped bg-warning"
                                  role="progressbar"
                                  aria-valuenow={caloriesTotal
                                  / calories * 100}
                                  aria-valuemin="0"
                                  aria-valuemax="100" style={{
                                  width: `${caloriesTotal
                                  / calories * 100}%`
                                }}></div>
                                </div>
                                <h6>Total Calories burned: {caloriesTotal}</h6>
                                <h6>Calories Goal: {calories}</h6>
                                <h6>Start Date: {caloriesGoalStartDate}</h6>
                                <h6>End Date: {caloriesGoalEndDate}</h6>
                              </div>
                          }
                        </div>
                    }
                  </div>
              }
              {
                  showDurationGoalForm &&
                  <div>
                    <div className="form-group">
                      <label htmlFor="duration"
                             className="form-label mt-4">Duration Goal</label>
                      <input type="text" className="form-control"
                             id="duration" placeholder="Duration Goal"
                             value={duration}
                             onChange={(e) => setDuration(e.target.value)}/>
                    </div>
                    <div className="form-group">
                      <label htmlFor="durationGoalStartDate"
                             className="form-label mt-2 me-2">Start Date</label>
                      <input type="date" className="form-control"
                             id="durationGoalStartDate"
                             value={durationGoalStartDate}
                             onChange={(e) => setDurationGoalStartDate(
                                 e.target.value)}/>
                    </div>
                    <div className="form-group">
                      <label htmlFor="durationGoalEndDate"
                             className="form-label mt-2 me-2">End Date</label>
                      <input type="date" className="form-control"
                             id="durationGoalEndDate"
                             value={durationGoalEndDate}
                             onChange={(e) => setDurationGoalEndDate(
                                 e.target.value)}/>
                    </div>
                    <button
                        type="submit" className="btn btn-primary mt-4"
                        onClick={handelSetDurationGoalBtn}>Set Goal
                    </button>
                    {
                        durationGoal &&
                        <div>
                          <button
                              type="submit"
                              className="btn btn-primary mt-4 mb-4"
                              onClick={handleTrackDurationProgressBtn}>Track My
                            Progress
                          </button>
                          {
                              showDurationProgress &&
                              <div>
                                <h5>You have completed {Math.round(
                                    durationTotal / duration * 100)}%
                                  of your duration goal!</h5>
                                <div className="progress mb-4">
                                  <div
                                      className="progress-bar progress-bar-striped bg-warning"
                                      role="progressbar"
                                      aria-valuenow={durationTotal
                                          / duration * 100}
                                      aria-valuemin="0"
                                      aria-valuemax="100" style={{
                                    width: `${durationTotal
                                    / duration * 100}%`
                                  }}></div>
                                </div>
                                <h6>Total Exercise Duration: {durationTotal}</h6>
                                <h6>Duration Goal: {duration}</h6>
                                <h6>Start Date: {durationGoalStartDate}</h6>
                                <h6>End Date: {durationGoalEndDate}</h6>
                              </div>
                          }
                        </div>
                    }
                  </div>
              }
            </div>
        }
      </div>
  )
};

export default PageTwoComponent;