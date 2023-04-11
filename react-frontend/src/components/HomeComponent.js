import React from 'react';
import HeaderComponent from "./HeaderComponent";

const HomeComponent = () => {
  return (
      <div>
        {<HeaderComponent/>}
        <div className="d-flex justify-content-between">
          <div className="container-wrapper">
            <img className="rounded-circle mx-auto d-block mt-2 mb-2"
                 src="https://i.pinimg.com/736x/12/48/1a/12481a3b73e9c553015dddc31480dd74.jpg"
                 alt="addExerciseImage" width="140" height="140"/>
            <div className="text-center">
              <h3>Add Exercise</h3>
              <p>Add an exercise and check your calories burned</p>
              <p><a className="btn btn-primary" href="add_exercise" role="button">Go »</a></p>
            </div>
          </div>
          <div className="container-wrapper">
            <img className="rounded-circle mx-auto d-block mt-2 mb-2"
                 src="https://i.pinimg.com/564x/eb/67/3a/eb673a826c4fb5985600653c03186cbb.jpg"
                 alt="setGoalImage" width="140" height="140"/>
            <div className="text-center">
              <h3>Set Goal</h3>
              <p>Set a goal and track your progress</p>
              <p><a className="btn btn-primary" href="set_goal" role="button">Go »</a></p>
            </div>
          </div>
          <div className="container-wrapper">
            <img className="rounded-circle mx-auto d-block mt-2 mb-2"
                 src="https://i.pinimg.com/564x/95/67/cb/9567cb98c98665aa8e448b37f16cb810.jpg"
                 alt="getExerciseSummaryImage" width="140" height="140"/>
            <div className="text-center">
              <h3>Exercise Summary</h3>
              <p>View your exercise summary and get suggestions</p>
              <p><a className="btn btn-primary" href="exercise_summary" role="button">Go »</a></p>
            </div>
          </div>
        </div>
      </div>
  )
};

export default HomeComponent;