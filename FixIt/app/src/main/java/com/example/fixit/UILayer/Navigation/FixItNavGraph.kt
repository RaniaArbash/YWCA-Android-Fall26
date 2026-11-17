package com.example.fixit.UILayer.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fixit.DataLayer.SpecialistDirectory
import com.example.fixit.UILayer.IssueViewModel
import com.example.fixit.UILayer.Screens.HomeScreen
import com.example.fixit.UILayer.Screens.IssueHistoryScreen
import com.example.fixit.UILayer.Screens.ReportIssueScreen
import com.example.fixit.UILayer.Screens.SpecialistProfileScreen
import com.example.fixit.UILayer.Screens.SpecialistsScreen


@Composable
fun FixItNavGraph(viewModel: IssueViewModel) {
    val navController = rememberNavController()
    val issues by viewModel.issues.collectAsState()
    val currentIssueId by viewModel.currentIssueId.collectAsState()

    NavHost(navController = navController,
        startDestination = "home") {
        composable("home") {
            HomeScreen(
                onNavigateReportIssue = { navController.navigate("report_issue") },
                onNavigateSpecialists = {
                    viewModel.setCurrentIssueId(null)
                    navController.navigate("specialists")
                },
                onNavigateIssueHistory = { navController.navigate("issue_history") }
            )
        }

        composable("report_issue") {
            ReportIssueScreen(
                onNavigateBack = { navController.popBackStack() },
                onIssueSubmitted = {
                    navController.navigate("specialists") {
                        popUpTo("home")
                    }
                },
                onSubmitIssue = { title, category, description ->
                    viewModel.addIssue(title = title, category = category, description = description)
                }
            )
        }

        composable("specialists") {
            SpecialistsScreen(
                onNavigateBack = { navController.popBackStack() },
                onSelectSpecialist = { specialistId ->
                    currentIssueId?.let { issueId ->
                        viewModel.updateIssueSpecialist(issueId, specialistId)
                    }
                    navController.navigate("specialist_profile/$specialistId")
                }
            )
        }

        composable(
            route = "specialist_profile/{specialistId}",
            arguments = listOf(navArgument("specialistId") { type = NavType.StringType })
        ) { backStackEntry ->
            val specialistId = backStackEntry.arguments?.getString("specialistId")
            val specialist = SpecialistDirectory.getSpecialist(specialistId)
            val currentIssue = issues.find { it.id == currentIssueId }

            SpecialistProfileScreen(
                specialist = specialist,
                currentIssue = currentIssue,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable("issue_history") {
            IssueHistoryScreen(
                issues = issues,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        }
    }
