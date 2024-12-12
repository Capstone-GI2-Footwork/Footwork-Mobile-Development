package com.gi2.footwork.features.Dashboard.presentation.atoms

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.*
import com.gi2.footwork.ui.theme.FootworkTheme
import com.gi2.footwork.ui.theme.primaryFixed

@Composable
fun DashboardLineChart(
  dataPoints: List<Point> = listOf(
    Point(0f, 200f),
    Point(1f, 0f),
    Point(2f, 630f),
    Point(3f, 220f),
    Point(4f, 440f),
    Point(5f, 800f),
    Point(6f, 420f)
  ),
) {
  if (dataPoints.size > 7) {
    throw IllegalArgumentException("The list 'xy' should not contain more than 7 points.")
  }

  val xAxisData = AxisData.Builder()
    .axisStepSize(80.dp)
    .backgroundColor(MaterialTheme.colorScheme.background)
    .steps(7) // represents the number of days in a week
    .labelData { index ->
      when (index) {
        0 -> "Mon"
        1 -> "Tue"
        2 -> "Wed"
        3 -> "Thu"
        4 -> "Fri"
        5 -> "Sat"
        6 -> "Sun"
        else -> ""
      }
    }
    .labelAndAxisLinePadding(24.dp)
    .axisLineColor(MaterialTheme.colorScheme.onSurfaceVariant)
    .axisLabelColor(MaterialTheme.colorScheme.onSurface)
    .build()

  val yAxisData = AxisData.Builder()
    .axisStepSize(64.dp)
    .backgroundColor(MaterialTheme.colorScheme.background)
    .steps(6) // from 0 to 1000 with a step of 200
    .labelData { index ->
      (index * 200).toString()
    }
    .labelAndAxisLinePadding(32.dp)
    .axisLineColor(MaterialTheme.colorScheme.onSurfaceVariant)
    .axisLabelColor(MaterialTheme.colorScheme.onSurface)
    .build()

  val lineChartData = LineChartData(
    xAxisData = xAxisData,
    yAxisData = yAxisData,
    linePlotData = LinePlotData(
      lines = listOf(
        Line(
          dataPoints = dataPoints,
          lineStyle = LineStyle(
            color = primaryFixed,
            lineType = LineType.SmoothCurve()
          ),
          intersectionPoint = IntersectionPoint(
            color = primaryFixed
          ),
          selectionHighlightPoint = SelectionHighlightPoint(
            color = MaterialTheme.colorScheme.primary,
          ),
          shadowUnderLine = ShadowUnderLine(
            alpha = 0.5f,
            brush = Brush.verticalGradient(
              colors = listOf(
                primaryFixed,
                Color.Transparent,
              )
            )
          )
        )
      )
    ),
    backgroundColor = MaterialTheme.colorScheme.background,
    gridLines = GridLines(
      color = MaterialTheme.colorScheme.surfaceContainerHigh,
      enableHorizontalLines = true,
      enableVerticalLines = false
    ),
  )

  Box(
    modifier = Modifier.fillMaxSize()
  ) {
    LineChart(
      modifier = Modifier.fillMaxSize(),
      lineChartData = lineChartData
    )
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DashboardLineChartPreview() {
  FootworkTheme {
    Scaffold(
      modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
      Box(
        modifier = Modifier
          .padding(innerPadding)
          .fillMaxWidth()
          .height(400.dp)
      ) {
        DashboardLineChart()
      }
    }
  }
}
