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
  const [caloriesTotal, setCaloriesTotal] = useState(0);
  const [durationTotal, setDurationTotal] = useState(0);
  const [caloriesGoal, setCaloriesGoal] = useState(0);
  const [durationGoal, setDurationGoal] = useState(0);
  const [underAveragedDurationList, setUnderAveragedDurationList] = useState(null);
  const [underAveragedCaloriesList, setUnderAveragedCaloriesList] = useState(null);
  const [showSuggestion, setShowSuggestion] = useState(false);
  const [aveCals, setAveCals] = useState(0);
  const [aveDur, setAveDur] = useState(0);

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
    if ((new Date(endDate) - new Date(startDate)) / (1000*60*60*24) + 1 > 20) {
      setError('The end date should not be more than 20 days later.')
      return
    }
    setError(null)
    try {
      const response = await UserService.getDailyCaloriesSumMap(userId, startDate, endDate);
      const response2 = await UserService.getDailyDurationSumMap(userId, startDate, endDate);
      const response3 = await UserService.getCaloriesTotalBetweenDates(userId, startDate, endDate);
      const response4 = await UserService.getDurationTotalBetweenDates(userId, startDate, endDate);
      const response5 = await UserService.getCaloriesGoalByUserId(userId);
      const response6 = await UserService.getDurationGoalByUserId(userId);

      setDailyCaloriesSumMap(new Map(Object.entries(response.data)));
      setDailyDurationSumMap(new Map(Object.entries(response2.data)));
      setCaloriesTotal(response3.data);
      setDurationTotal(response4.data);
      console.log(response5);
      setCaloriesGoal(response5.data["caloriesGoal"]);
      setDurationGoal(response6.data["durationGoal"]);
      setGenerateReport(true);

    } catch (e) {
      setError('Failed to generate report based on your input. Please try again.');
    }
  }

  const handleViewReportBtn = async(e) => {
    try {
      e.preventDefault();
      console.log(typeof caloriesGoal);
      const response11 = await UserService.getUnderAveragedCaloriesDateBetweenDates(userId, caloriesGoal, startDate, endDate);
      const response12 = await UserService.getUnderAveragedDurationDateBetweenDates(userId, durationGoal, startDate, endDate);
      console.log(response11);
      setUnderAveragedCaloriesList(response11.data.map(x => (x.slice(0, 10))).sort());
      setUnderAveragedDurationList(response12.data.map(x => (x.slice(0, 10))).sort());
      console.log(underAveragedDurationList);

      const transferredCPair = new Map([...dailyCaloriesSumMap.entries()].sort());
      const transferredDPair = new Map([...dailyDurationSumMap.entries()].sort());
      const diff = (new Date(endDate) - new Date(startDate)) / (1000*60*60*24) + 1;
      setAveCals(caloriesGoal / diff);
      setAveDur(durationGoal / diff);

      setCaloriesData({
        labels: Array.from( transferredCPair.keys() ).map(function(v) { return v.slice(0, 10) }).slice(0, 20),
        datasets: [{
          label: "Calories (kcals)",
          data: Array.from( transferredCPair.values() ).slice(0, 20),
        }]
      });
      setDurationData({
        labels: Array.from( transferredDPair.keys() ).map(function(v) { return v.slice(0, 10) }).slice(0, 20),
        datasets: [{
          label: "Duration (mins)",
          data: Array.from( transferredDPair.values() ).slice(0, 20),
        }]
      })

      setShowChart(true);
      setShowSuggestion(true);
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
                <h4 className="alert-heading">Calories Goal Suggestions</h4>
                {showSuggestion &&
                (caloriesTotal < caloriesGoal) &&
                  <div>
                    <p>You are {caloriesGoal - caloriesTotal} kcals away from your calories goal {caloriesGoal}. </p>
                    <p> Your suggested daily calories is {aveCals.toFixed(2)} kcals. </p>
                    <p>We suggest you to exercise more on at least one of the following days to reach your calories goal: {underAveragedCaloriesList.map(x => x + ". ")} </p>
                  </div>
                }
                {showSuggestion &&
                    (caloriesTotal >= caloriesGoal) &&
                    <div>
                      <p> Congratulations! You have reach your calories goal {caloriesGoal}! </p>
                    </div>
                }
                <h4 className="alert-heading">Duration Goal Suggestions</h4>
                {showSuggestion &&
                    (durationTotal >= durationGoal) &&
                    <div>
                      <p> Congratulations! You have reach your duration goal {durationGoal}! </p>
                    </div>
                }

                {showSuggestion &&
                    (durationTotal < durationGoal) &&
                    <div>
                      <p>You are {durationGoal - durationTotal} mins away from your duration goal {durationGoal}. </p>
                      <p> Your suggested daily duration is {aveDur.toFixed(2)} mins. </p>
                      <p>We suggest you to exercise more on at least one of the following days to reach your duration goal: {underAveragedDurationList.map(x => x + ". ")} </p>
                    </div>
                }
              </div>
            </div>
        }
    </div>
  );
};

export default ExerciseSummaryComponent;