package com.example.chuck.util

class ImgUrls {
    companion object {
        private const val chuck = "https://i.pinimg.com/280x280_RS/29/57/c0/2957c065942ebb18b3a941a4068fd9ef.jpg"
        private const val animal = "https://ranktopten.com/upload/o3/ym/top-weird-animals_c_200x200.jpg"
        private const val career = "https://careers.jpmorgan.com/content/dam/careers/icons_new/icon-launching-a-career-200px.png"
        private const val celebrity = "https://assets.teenvogue.com/photos/55828f24941a95530d042e96/1:1/w_200,h_200,c_limit/beauty-celebrity-beauty-2015-02-kylie-jenner-grammys-th.jpg"
        private const val dev = "https://media-exp1.licdn.com/dms/image/C4E0BAQFo9Q282ehaPg/company-logo_200_200/0/1552401045341?e=2147483647&v=beta&t=oZnHkwEPGMljK0DSBr4R_tYBCXASt5TzJNQh-0eEVuQ"
        private const val explicit = "https://media-exp1.licdn.com/dms/image/C4D0BAQFjk6T1BAmy4g/company-logo_200_200/0/1579743386091?e=2159024400&v=beta&t=KIRHNK70B5QiTODs4Jf2kWy-ROW0f8vfIx0_gaa2Oqk"
        private const val fashion = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvUcWcNhXKufheSnv9K7Pel2xkUavN70Vbxw&usqp=CAU"
        private const val food = "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8-200x200.jpg"
        private const val history = "https://www.iahn.info/wp-content/uploads/2018/09/logo-iahn-new-200x200.png"
        private const val money = "https://scx1.b-cdn.net/csz/news/800a/money.jpg"
        private const val movie = "https://static.vecteezy.com/system/resources/thumbnails/002/608/339/small/mobile-application-movie-video-and-film-web-button-menu-digital-silhouette-style-icon-free-vector.jpg"
        private const val music = "https://arkallsaintsacademy.org/sites/default/files/resize/music_0-200x200.jpg"
        private const val political = "https://static.vecteezy.com/system/resources/thumbnails/004/190/715/small/elections-concept-icon-political-debate-talking-to-election-opponent-idea-thin-line-illustration-political-campaign-presidential-race-isolated-outline-drawing-editable-stroke-vector.jpg"
        private const val religion = "https://charbase.com/images/glyph/82"
        private const val science = "https://cdn.files.pg.edu.pl/main/Data%20Science%20Club/logom.png"
        private const val sport = "https://i0.wp.com/www.esleschool.com/wp-content/uploads/2018/05/sports-e1641237015887.png?fit=200%2C200&ssl=1"
        private const val travel = "https://www.nasa.gov/images/content/179215main_earth-globe-200.jpg"
        val urlList = arrayListOf(chuck, animal, career, celebrity, dev, explicit, fashion, food, history, money, movie, music, political, religion, science, sport, travel)
        var hashmap: HashMap<String, String> = hashMapOf(
            "animal" to animal,
            "career" to career,
            "celebrity" to celebrity,
            "dev" to dev,
            "explicit" to explicit,
            "fashion" to fashion,
            "food" to food,
            "history" to history,
            "money" to money,
            "movie" to movie,
            "music" to music,
            "political" to political,
            "religion" to religion,
            "science" to science,
            "sport" to sport,
            "travel" to travel
        )
    }
}