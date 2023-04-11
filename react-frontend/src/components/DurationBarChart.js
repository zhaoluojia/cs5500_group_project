import {Bar} from 'react-chartjs-2';
import {Chart as ChartJS} from 'chart.js/auto';
import React from 'react'

function DurationBarChart({ chartData }) {

  return <Bar data={chartData}/>;
}
export default DurationBarChart;