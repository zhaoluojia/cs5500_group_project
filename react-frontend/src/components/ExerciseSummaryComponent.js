import React, {useState} from 'react'
import UserService from "../services/UserService";
import CaloriesBarChart from "./CaloriesBarChart";
import DurationBarChart from "./DurationBarChart";
import HeaderComponent from "./HeaderComponent";

const ExerciseSummaryComponent = () => {
  const [error, setError] = useState(null);
  const [userName, setUserName] = useState('');
  const [userExist, setUserExist] = useState(false);
  const [userId, setUserId] = useState('');
  const [startDate, setStartDate] = useState('')
  const [endDate, setEndDate] = useState('')
  const [dailyCaloriesSumMap, setDailyCaloriesSumMap] = useState(null)
  const [dailyDurationSumMap, setDailyDurationSumMap] = useState(null)
  const [caloriesData, setCaloriesData] = useState(null);
  const [durationData, setDurationData] = useState(null);
  const [generateReport, setGenerateReport] = useState(false);
  const [showChart, setShowChart] = useState(false);
  const [smallestDurationDate, setSmallestDurationDate] = useState(null);
  const [smallestCaloriesDate, setSmallestCaloriesDate] = useState(null);

  const handleUserSubmitBtn = async (e) => {
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

  const handleGenerateReportBtn = async (e) => {
    e.preventDefault();
    if (startDate === '') {
      setError('Please select a start date')
      return
    }
    if (endDate === '') {
      setError('Please select an end date')
      return
    }
    if (startDate > endDate) {
      setError('The end date cannot be before the start date')
      return
    }
    setError(null)
    try {
      const response = await UserService.getDailyCaloriesSumMap(userId, startDate, endDate);
      const response2 = await UserService.getDailyDurationSumMap(userId, startDate, endDate);
      const response3 = await UserService.getSmallestCaloriesDateBetweenDates(userId, startDate, endDate);
      const response4 = await UserService.getSmallestDurationDateBetweenDates(userId, startDate, endDate);
      setDailyCaloriesSumMap(new Map(Object.entries(response.data)));
      setDailyDurationSumMap(new Map(Object.entries(response2.data)));
      setSmallestCaloriesDate(response3.data);
      setSmallestDurationDate(response4.data);

      console.log(response.data);
      console.log(response2.data);
      console.log(dailyCaloriesSumMap);
      console.log(dailyDurationSumMap);
      setGenerateReport(true);
      console.log(generateReport);
    } catch (e) {
      setError('Failed to generate report based on your input. Please try again.');
    }
  }

  const handleViewReportBtn = () => {
    try {
      setCaloriesData({
        labels: Array.from( dailyCaloriesSumMap.keys() ).map(function(v) { return v.slice(0, 10) }),
        datasets: [{
          label: "Calories (kcals)",
          data: Array.from( dailyCaloriesSumMap.values() ),
        }]
      });
      setDurationData({
        labels: Array.from( dailyDurationSumMap.keys() ).map(function(v) { return v.slice(0, 10) }),
        datasets: [{
          label: "Duration (mins)",
          data: Array.from( dailyDurationSumMap.values() ),
        }]
      })

      setShowChart(true);
    } catch (e) {
      setError('Failed to view report. Please try again.');
    }
  }

  return (
      <div>
        {<HeaderComponent/>}
        <h2 className="mt-4">Exercise Summary</h2>
        {
            error &&
            <div className="alert alert-dismissible alert-danger mt-3 mb-0">
              {error}
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
            onClick={handleUserSubmitBtn}>Submit
        </button>
        {
            userExist &&
            <div>
              <div className="form-group">
                <label htmlFor="caloriesGoalStartDate"
                       className="form-label mt-2">Start Date</label>
                <input type="date" className="form-control"
                       id="caloriesStartDate"
                       value={startDate}
                       onChange={(e) => setStartDate(
                           e.target.value)}/>
              </div>
              <div className="form-group">
                <label htmlFor="caloriesGoalStartDate"
                       className="form-label mt-2">End Date</label>
                <input type="date" className="form-control"
                       id="caloriesEndDate"
                       value={endDate}
                       onChange={(e) => setEndDate(
                           e.target.value)}/>
              </div>
            </div>
        }
        {
            startDate && endDate &&
            <div>
              <div>
                <button
                    type="submit" className="btn btn-primary mt-4"
                    onClick={handleGenerateReportBtn}>Generate Report
                </button>
              </div>

            </div>
        }
        {
            generateReport &&
            <div>
              <p className="text-secondary mt-2">Your report is generated successfully!</p>
              <button
                  type="submit" className="btn btn-primary mt-2 mb-2"
                  onClick={handleViewReportBtn}>View Report
              </button>
            </div>
        }
        {
            showChart &&
            <div>
              <div className="d-flex flex-row">
                <div className="d-flex justify-content-center w-50 h-50">
                  <CaloriesBarChart chartData={caloriesData}/>
                </div>
                <div className="d-flex justify-content-center w-50 h-50">
                  <DurationBarChart chartData={durationData}/>
                </div>
              </div>
              <div className="alert alert-dismissible alert-primary mt-4">
                <button type="button" className="btn-close"
                        data-bs-dismiss="alert"></button>
                <h4 className="alert-heading">Suggestions</h4>
                <p className="mb-0">We suggest you to exercise more on {smallestDurationDate.slice(0, 10)} to reach your duration goal.</p>
                <p className="mb-0">We suggest you to exercise more on {smallestCaloriesDate.slice(0, 10)} to reach your calories goal.</p>
              </div>
            </div>
        }
    </div>
  );
};

export default ExerciseSummaryComponent;