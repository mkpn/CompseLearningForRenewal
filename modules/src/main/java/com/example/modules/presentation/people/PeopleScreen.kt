package com.example.modules.presentation.people

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PeopleScreen(onContentSelected: (Long) -> Unit) {
    val members = listOf(
        "Alice Adams",
        "Aiden Anderson",
        "Amelia Allen",
        "Adam Archer",
        "Anna Austin",
        "Benjamin Brown",
        "Bella Bennett",
        "Brandon Baker",
        "Beatrice Brooks",
        "Brian Barnes",
        "Catherine Clark",
        "Charles Carter",
        "Chloe Cooper",
        "Christopher Coleman",
        "Caroline Campbell",
        "David Davis",
        "Diana Diaz",
        "Daniel Dunn",
        "Donna Douglas",
        "Derek Dixon",
        "Emily Evans",
        "EthanmEdwards",
        "Emma Ellis",
        "Eric Ewing",
        "Ella Eubanks",
        "Frank Foster",
        "Faith Fisher",
        "Felix Ferguson",
        "Fiona Ford",
        "Freddie Franklin",
        "Grace Green",
        "Gabriel Gray",
        "Gemma Griffin",
        "George Gordon",
        "Gloria Gomez",
        "Henry Harris",
        "Hannah Hall",
        "Harry Hayes",
        "Holly Hernandez",
        "Harper Harper",
        "Isabel Ingram",
        "Ian Irwin",
        "Ivy Ibarra",
        "Isaiah Ives",
        "Irene Izquierdo",
        "John Johnson",
        "Julia Jackson",
        "James Jones",
        "Jessica Jenkins",
        "Jacob Johnson",
        "Karen King",
        "Kevin Kelly",
        "Katherine Kim",
        "Kyle Knight",
        "Kayla Kramer",
        "Liam Lewis",
        "Lily Long",
        "Lucas Lee",
        "Leah Lawrence",
        "Logan Lambert",
        "Mia Mitchell",
        "Michael Morris",
        "Madison Martin",
        "Matthew Marshall",
        "Mila Murphy",
        "Noah Nelson",
        "Nora Nichols",
        "Nathan Newman",
        "Natalie Norris",
        "Nicholas Norton",
        "Olivia Olson",
        "Owen Owens",
        "Oliver Ortiz",
        "Olga O'Donnell",
        "Oscar Oliver",
        "Peter Parker",
        "Penelope Palmer",
        "Patrick Pearson",
        "Paige Phillips",
        "Preston Porter",
        "Quincy Quinn",
        "Quinn Quinn",
        "Quintin Quinn",
        "Queenie Quinn",
        "Quentin Quinn",
        "Ruby Robinson",
        "Robert Rodriguez",
        "Rachel Roberts",
        "Ryan Russell",
        "Rebecca Rivera",
        "Samuel Smith",
        "Sophia Scott",
        "Sebastian Stewart",
        "Sarah Sanders",
        "Steven Stevens",
        "Taylor Turner",
        "Thomas Taylor",
        "Trinity Thompson",
        "Theodore Thomas",
        "Tessa Tucker",
        "Ursula Underwood",
        "Uriah Upton",
        "Ulrich Ulman",
        "Ulysses Urban",
        "Uma Upton",
        "Victor Vaughn",
        "Vanessa Vega",
        "Vincent Valencia",
        "Valerie Vasquez",
        "Violet Vaughn",
        "William Walker",
        "Wendy Wallace",
        "Walter Ward",
        "Whitney Watson",
        "Wesley Webb",
        "Xander Xavier",
        "Ximena Xiong",
        "Xavier Xiong",
        "Xena Xu",
        "Xaviera Xiong",
        "Yvonne Young",
        "Yasmine York",
        "Yael Yates",
        "Yara Yoder",
        "Ysabella Yang",
        "Zoe Zane",
        "Zachary Zuniga",
        "Zara Zimmerman",
        "Zachariah Zhang",
        "Zariah Zavala"
    )

    val grouped = members.groupBy { it.first() }

    LazyColumn {
        grouped.forEach { (initial, contactsForInitial) ->
            stickyHeader {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Gray)
                        .padding(8.dp),
                    text = initial.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )
            }
            items(contactsForInitial.size) { index ->
                ClickableText(
                    text = AnnotatedString(contactsForInitial[index]),
                    style = TextStyle(
                        fontSize = 24.sp
                    )
                ) {
                    onContentSelected(1)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun プレビュー_PeopleScreen() {
    PeopleScreen {}
}