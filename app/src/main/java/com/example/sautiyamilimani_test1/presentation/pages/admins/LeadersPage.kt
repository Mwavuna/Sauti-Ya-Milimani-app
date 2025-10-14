package com.example.sautiyamilimani_test1.presentation.pages.admins

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.AutoGraph
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.NoteAlt
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.TaskAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sautiyamilimani_test1.R
import com.example.sautiyamilimani_test1.presentation.navigation.Screen
import com.example.sautiyamilimani_test1.presentation.viewmodels.AuthViewModel
import com.example.sautiyamilimani_test1.presentation.viewmodels.MembersViewModel
import kotlinx.coroutines.launch
import kotlin.math.tanh

@Composable
fun LeadersPage(
    modifier: Modifier,
    navController: NavController,
    membersViewModel: MembersViewModel,
    authViewModel: AuthViewModel
) {

    val totalMembers by membersViewModel.memberCount.collectAsState()

    val animatedTotalMembers by animateIntAsState(targetValue = totalMembers, label = "memberAnim")

    var memberIncrement by rememberSaveable { mutableStateOf(+0) }

    var activeThisMonth by rememberSaveable { mutableStateOf(0) }
    var activeThisMonthRate by rememberSaveable { mutableStateOf(0) }


    var newThisQuarter by rememberSaveable { mutableStateOf(0) }
    var newThisQuarterIncrement by rememberSaveable { mutableStateOf(0) }

    var attendanceRate by rememberSaveable { mutableStateOf(0) }
    var attendanceRateIncrement by rememberSaveable { mutableStateOf(0) }

    val borderStroke: BorderStroke = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(.1f))

    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row( horizontalArrangement = Arrangement.Center) {
                    Text("Leader")
                }
                Row() {
                    val scope = rememberCoroutineScope()
                    TextButton(onClick = {
                        scope.launch {
                            authViewModel.logout()
                        }
                    }) {
                        Text("Logout")
                    }

                }
            }
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {

            Column {

                //Statistics Section
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(8.dp)
                ) {

                    StatCard(
                        modifier = Modifier
                            .weight(1f),
                        "Total Members",
                        "$animatedTotalMembers",
                        "$memberIncrement"
                    )
                    StatCard(
                        modifier = Modifier
                            .weight(1f),
                        "Active This Month",
                        "$activeThisMonth",
                        "$activeThisMonthRate%"
                    )


                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(8.dp)
                ) {

                    StatCard(
                        modifier = Modifier
                            .weight(1f),
                        "New This Quarter",
                        "$newThisQuarter",
                        "$newThisQuarterIncrement"
                    )
                    StatCard(
                        modifier = Modifier
                            .weight(1f),
                        "Attendance Rate",
                        "$attendanceRate%",
                        "$attendanceRateIncrement%"
                    )


                }

                //Quick Actions section
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .border(borderStroke, MaterialTheme.shapes.small)
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
                ) {
                    Column {

                        //Quick Actions Section Title
                        Text(
                            "Quick Actions",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.headlineMedium
                        )

                        Spacer(Modifier.height(4.dp))
                        Text(
                            "Common tasks for your role",
                            color = MaterialTheme.colorScheme.primary.copy(.5f),
                        )


                        //Manage Members and Events Buttons
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            //Manage members button
                            ActionButton(
                                title = "Manage Members",
                                icon = Icons.Outlined.People,
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    navController.navigate(Screen.MembersManagement.route)
                                }
                            )


                            //Events Button
                            ActionButton(
                                title = "Manage Events",
                                icon = Icons.Outlined.Event,
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    navController.navigate(Screen.Events.route)
                                }
                            )

                        }

                        //Projects and Songs buttons
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            //Projects Button
                            ActionButton(
                                title = "Projects",
                                icon = Icons.Outlined.AttachMoney,
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    navController.navigate(Screen.Projects.route)
                                }
                            )


                            //Songs Button
                            ActionButton(
                                title = "Songs",
                                icon = Icons.Outlined.MusicNote,
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    navController.navigate(Screen.Songs.route)
                                }
                            )


                        }


                        //Attendance and Minutes Buttons
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            //Attendance Button
                            ActionButton(
                                title = "Attendance",
                                icon = Icons.Outlined.TaskAlt,
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    navController.navigate(Screen.Attendance.route)
                                }
                            )


                            //Minutes Button
                            ActionButton(
                                title = "Minutes",
                                icon = Icons.Outlined.NoteAlt,
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    navController.navigate(Screen.Minutes.route)
                                }
                            )

                        }

                    }

                }

            }

        }
    }

}

@Composable
fun ActionButton(title: String, icon: ImageVector, modifier: Modifier, onClick: () -> Unit) {
    OutlinedButton(
        onClick = { onClick() },
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(
            1.dp, MaterialTheme.colorScheme.outline.copy(.1f)
        ),
        modifier = modifier
            .height(80.dp),
        colors = ButtonDefaults.outlinedButtonColors()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 4.dp)

        ) {
            Icon(
                icon,
                title,
                modifier = Modifier
                    .size(20.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(title)
        }
    }
}

@Composable
fun StatCard(modifier: Modifier, title: String, stat: String, increment: String) {

    Card(
        modifier = modifier
            .border(
                1.dp,
                MaterialTheme.colorScheme.outline.copy(.1f),
                MaterialTheme.shapes.medium
            )
            .height(80.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)

    ) {

        Row(

            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    title,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary.copy(.5f),
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    stat,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }


            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Outlined.AutoGraph,
                    "null",
                    tint = when {
                        increment.contains("-") -> Color.Red
                        increment == "0" -> Color.Gray
                        else -> Color(0xFF10701E)
                    },
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    increment,
                    color = when {
                        increment.contains("-") -> Color.Red
                        increment == "0" -> Color.Gray
                        else -> Color(0xFF10701E)
                    }
                )
            }
        }
    }
}

