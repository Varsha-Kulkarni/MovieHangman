/*
 * Copyright 2023 Varsha Kulkarni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.varshakulkarni.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "movie", indices = [Index(value = ["title"], unique = true)])
data class MovieEntity(
    val title: String,
    val description: String,
    val media: String?,
    val isPlayed: Boolean,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    companion object {
        fun populateData(): Array<MovieEntity> {
            return arrayOf(

                MovieEntity(
                    "The Good, the Bad and the Ugly",
                    "The Good, the Bad and the Ugly is a 1966 Italian epic spaghetti Western film directed by Sergio Leone. During the Civil War, two men, Blondie and Tuco, form an uncomfortable alliance while looking for treasure. They must also outwit Angel Eyes, an outlaw who wants to plunder the riches for himself.",
                    "",
                    false,
                ),

                MovieEntity(
                    "The Silence of the Lambs",
                    "The Silence of the Lambs is a 1991 American psychological horror film directed by Jonathan Demme. Clarice Starling, an FBI agent, seeks help from Hannibal Lecter, a psychopathic serial killer and former psychiatrist, in order to apprehend another serial killer who has been claiming female victims.",
                    "",
                    false,
                ),
                MovieEntity(
                    "Saving Private Ryan",
                    "Saving Private Ryan is a 1998 American epic war film directed by Steven Spielberg. During the Normandy invasion of World War II, Captain John Miller is assigned the task of searching for Private James Ryan, whose three brothers have already been killed in the war.",
                    "",
                    false,
                ),
                MovieEntity(
                    "Interstellar",
                    "Interstellar is a 2014 epic science fiction film co-written, directed, and produced by Christopher Nolan. When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.",
                    "",
                    false,
                ),
                MovieEntity(
                    "Life Is Beautiful",
                    "Life Is Beautiful is a 1997 Italian comedy drama film directed by and starring Roberto Benigni. A Jewish father and his family are surrounded by Nazi death camps. Living in a hostile environment, he uses humour to shield his young son from the grim realities of the war.",
                    "",
                    false,
                ),
                MovieEntity(
                    "The Shawshank Redemption",
                    "The Shawshank Redemption is a 1994 American drama film written and directed by Frank Darabont, based on the 1982 Stephen King novella Rita Hayworth and Shawshank Redemption. Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion.",
                    "",
                    false,
                ),

                MovieEntity(
                    "The Godfather",
                    "The Godfather is a 1972 American crime film directed by Francis Ford Coppola. Don Vito Corleone, head of a mafia family, decides to hand over his empire to his youngest son Michael. However, his decision unintentionally puts the lives of his loved ones in grave danger.",
                    "",
                    false,
                ),
                MovieEntity(
                    "The Dark Knight",
                    "The Dark Knight is a 2008 superhero film directed by Christopher Nolan. After Gordon, Dent and Batman begin an assault on Gotham's organised crime, the mobs hire the Joker, a psychopathic criminal mastermind who offers to kill Batman and bring the city to its knees.",
                    "",
                    false,
                ),
                MovieEntity(
                    "12 Angry Men",
                    "12 Angry Men is a 1957 American courtroom drama film directed by Sidney Lumet, adapted from a 1954 teleplay of the same name by Reginald Rose. A dissenting juror in a murder trial slowly manages to convince the others that the case is not as obviously clear as it seemed in court.",
                    "",
                    false,
                ),
                MovieEntity(
                    "Schindler's List",
                    "Schindler's List is a 1993 American epic historical drama film directed and produced by Steven Spielberg. Oskar Schindler, a German industrialist and member of the Nazi party, tries to save his Jewish employees after witnessing the persecution of Jews in Poland.",
                    "",
                    false,
                ),
                MovieEntity(
                    "Pulp Fiction",
                    "Pulp Fiction is a 1994 American crime film written and directed by Quentin Tarantino. In the realm of underworld, a series of incidents intertwines the lives of two Los Angeles mobsters, a gangster's wife, a boxer and two small-time criminals.",
                    "",
                    false,
                ),
                MovieEntity(
                    "Forrest Gump",
                    "Forrest Gump is a 1994 American comedy-drama film directed by Robert Zemeckis, it is based on the 1986 novel of the same name by Winston Groom. Forrest, a man with low IQ, recounts the early years of his life when he found himself in the middle of key historical events. All he wants now is to be reunited with his childhood sweetheart, Jenny.",
                    "",
                    false,
                ),
                MovieEntity(
                    "Fight Club",
                    "Fight Club is a 1999 American thriller-drama film directed by David Fincher. Unhappy with his capitalistic lifestyle, a white-collared insomniac forms an underground fight club with Tyler, a careless soap salesman. Soon, their venture spirals down into something sinister.",
                    "",
                    false,
                ),
                MovieEntity(
                    "Inception",
                    "Inception is a 2010 science fiction action film written and directed by Christopher Nolan. Cobb steals information from his targets by entering their dreams. Saito offers to wipe clean Cobb's criminal history as payment for performing an inception on his sick competitor's son.",
                    "",
                    false,
                ),
                MovieEntity(
                    "The Matrix",
                    "The Matrix is a 1999 science fiction action film written and directed by the Wachowskis. When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.",
                    "",
                    false,
                ),
                MovieEntity(
                    "Goodfellas",
                    "Goodfellas is a 1990 American biographical crime film directed by Martin Scorsese. Young Henry Hill, with his friends Jimmy and Tommy, begins the climb from being a petty criminal to a gangster on the mean streets of New York.",
                    "",
                    false,
                ),
                MovieEntity(
                    "One Flew Over the Cuckoo's Nest",
                    "One Flew Over the Cuckoo's Nest is a 1975 American psychological comedy drama film directed by Miloš Forman, based on the 1962 novel of the same name by Ken Kesey. In order to escape the prison labour, McMurphy, a prisoner, fakes insanity and is shifted to the special ward for the mentally unstable. In this ward, he must rise up against a cruel nurse, Ratched.",
                    "",
                    false,
                ),
                MovieEntity(
                    "Se7en",
                    "Seven (stylized as Se7en) is a 1995 American crime thriller film directed by David Fincher. A serial killer begins murdering people according to the seven deadly sins. Two detectives, one new to the city and the other about to retire, are tasked with apprehending the criminal.",
                    "",
                    false,
                ),
                MovieEntity(
                    "It's a Wonderful Life",
                    "It's a Wonderful Life is a 1946 American Christmas fantasy drama film produced and directed by Frank Capra. When a frustrated businessman, George Bailey, becomes suicidal, an angel from heaven, Clarence, is sent to him. To his change of heart, she shows him what life would have been without his existence.",
                    "",
                    false,
                ),
            )
        }
    }
}
