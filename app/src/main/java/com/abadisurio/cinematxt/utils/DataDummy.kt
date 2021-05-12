package com.abadisurio.cinematxt.utils

import com.abadisurio.cinematxt.data.MovieEntity
import com.abadisurio.cinematxt.data.TVShowEntity

object DataDummy {
    fun generateDummyMovies(): List<MovieEntity>{
        val movies = ArrayList<MovieEntity>()

        movies.add(MovieEntity(
            "1",
            "Alita: Battle Angel",
            "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
            "02/14/2019",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xRWht48C2V8XNfzvPehyClOvDni.jpg"
        ))
        movies.add(MovieEntity(
            "2",
            "Aquaman",
            "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
            "12/21/2018",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5Kg76ldv7VxeX9YlcQXiowHgdX6.jpg"
        ))
        movies.add(MovieEntity(
            "3",
            "Overlord",
            "France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else.",
            "11/09/2018",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/l76Rgp32z2UxjULApxGXAPpYdAP.jpg"
        ))
        movies.add(MovieEntity(
            "4",
            "Wreck-It Ralph",
            "Wreck-It Ralph is the 9-foot-tall, 643-pound villain of an arcade video game named Fix-It Felix Jr., in which the game's titular hero fixes buildings that Ralph destroys. Wanting to prove he can be a good guy and not just a villain, Ralph escapes his game and lands in Hero's Duty, a first-person shooter where he helps the game's hero battle against alien invaders.",
            "11/02/2012",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/zWoIgZ7mgmPkaZjG0102BSKFIqQ.jpg"
        ))
        movies.add(MovieEntity(
            "5",
            "Glass",
            "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
            "01/18/2019",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg"
        ))
        movies.add(MovieEntity(
            "6",
            "Avengers: Infinity War",
            "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality.",
            "04/27/2018",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1uoU4qPZEcOus0rQf8Tz4JCn3cX.jpg"
        ))
        movies.add(MovieEntity(
            "7",
            "How to Train Your Dragon",
            "As the son of a Viking leader on the cusp of manhood, shy Hiccup Horrendous Haddock III faces a rite of passage: he must kill a dragon to prove his warrior mettle. But after downing a feared dragon, he realizes that he no longer wants to destroy it, and instead befriends the beast – which he names Toothless – much to the chagrin of his warrior father",
            "03/26/2010",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/ygGmAO60t8GyqUo9xYeYxSZAR3b.jpg"
        ))
        movies.add(MovieEntity(
            "8",
            "Robin Hood (2018)",
            "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
            "11/21/2018",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/AiRfixFcfTkNbn2A73qVJPlpkUo.jpg"
        ))
        movies.add(MovieEntity(
            "9",
            "Spider-Man: Into the Spider-Verse",
            "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
            "12/14/2018",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg"
        ))
        movies.add(MovieEntity(
            "10",
            "Mortal Engines",
            "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
            "12/14/2018",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gLhYg9NIvIPKVRTtvzCWnp1qJWG.jpg"
        ))
        movies.add(MovieEntity(
            "11",
            "A Star Is Born (2018)",
            "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            "10/5/2018",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg"
        ))

        return movies
    }

    fun generateDummyTVShows(): List<TVShowEntity>{
        val shows = ArrayList<TVShowEntity>()

        shows.add(TVShowEntity(
            "1",
            "Arrow",
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            "2012",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg"
        ))
        shows.add(TVShowEntity(
            "2",
            "Doom Patrol",
            "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
            "2019",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/drlfSCIlMKrEeMPhi8pqY4xGxj.jpg"
        ))
        shows.add(TVShowEntity(
            "3",
            "Dragon Ball",
            "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
            "1986",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tZ0jXOeYBWPZ0OWzUhTlYvMF7YR.jpg"
        ))
        shows.add(TVShowEntity(
            "4",
            "Family Guy",
            "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). ",
            "1999",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/eWWCRjBfLyePh2tfZdvNcIvKSJe.jpg"
        ))
        shows.add(TVShowEntity(
            "5",
            "The Flash",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good.",
            "2014",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg"
        ))
        shows.add(TVShowEntity(
            "6",
            "Gotham",
            "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
            "2014",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4XddcRDtnNjYmLRMYpbrhFxsbuq.jpg"
        ))
        shows.add(TVShowEntity(
            "7",
            "Grey's Anatomy",
            "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
            "2005",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg"
        ))
        shows.add(TVShowEntity(
            "8",
            "The Umbrella Academy",
            "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
            "2019",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg"
        ))
        shows.add(TVShowEntity(
            "9",
            "NCIS",
            "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
            "2003",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fi8EvaWtL5CvoielOjjVvTr7ux3.jpg"
        ))
        shows.add(TVShowEntity(
            "10",
            "Riverdale",
            "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
            "2017",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg"
        ))
        shows.add(TVShowEntity(
            "11",
            "The Simpson",
            "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
            "1989",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg"
        ))

        return shows
    }
}