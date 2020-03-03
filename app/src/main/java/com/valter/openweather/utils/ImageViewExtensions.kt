package com.valter.openweather.utils

fun String.buildIconUrl() = StringBuilder("http://openweathermap.org/img/wn/${this}@2x.png")
            .insert(4, "s")
            .toString()

fun String.getWeatherImage() : String {
    return when(this) {
        "01d" -> "https://image.freepik.com/free-photo/shining-sun-clear-blue-sky-with-free-text-space_34683-41.jpg"
        "02d" -> "https://images2.minutemediacdn.com/image/upload/c_crop,h_1193,w_2121,x_0,y_221/f_auto,q_auto,w_1100/v1555155296/shape/mentalfloss/iStock-104472907.jpg"
        "03d" -> "https://www.ksnt.com/wp-content/uploads/sites/86/2018/05/mostlycloudy_36357908_ver1.0-1.jpg"
        "04d" -> "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/625a747a-061b-477d-958f-a0d6cea9e4cb/dax9bd4-dd0da73d-5b6e-415c-b05e-19471f366e5a" +
                ".jpg/" +
                "v1/fill/w_1024,h_768,q_75,strp/broken_clouds_by_kevintheman_dax9bd4-fullview.jpg" +
                "?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTg" +
                "yMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9NzY4IiwicGF0aCI6IlwvZlwvNjI1YTc0N2EtMDYxYi00NzdkLTk1OGYtYTBkNmNlYTllNGNiXC9kYXg5Y" +
                "mQ0LWRkMGRhNzNkLTViNmUtNDE1Yy1iMDVlLTE5NDcxZjM2NmU1YS5qcGciLCJ3aWR0aCI6Ijw9MTAyNCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS5vcGVyYXRpb25zIl19.2H" +
                "BtScMyydNDUe606gk2Jd8RHs6iM-76feSI7Dc3sLw"
        "09d" -> "https://wallpaperaccess.com/full/1379504.png"
        "10d" -> "https://live.staticflickr.com/4763/25092881517_0bdc73d9b6_b.jpg"
        "11d" -> "https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Lightning_Pritzerbe_01_%28MK%29.jpg/1200px-Lightning_Pritzerbe_01_%28MK%29.jpg"
        "13d" -> "https://images.unsplash.com/photo-1454692173233-f4f34c12adad?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80"
        "50d" -> "https://1.bp.blogspot.com/_czJKXjlwzts/TDY2fyfVwvI/AAAAAAAAAAc/n21HsQxuf14/s1600/shanghai.jpg"

        "01n" -> "https://i.pinimg.com/originals/db/78/5c/db785c75f2cbbf85af41bcc3578d994d.jpg"
        "02n" -> "https://zaaranzowani.pl/wp-content/uploads/fotolia_91343272.jpg"
        "03n" -> "https://ak0.picdn.net/shutterstock/videos/12703460/thumb/1.jpg"
        "04n" -> "https://live.staticflickr.com/7154/6769034555_ecb8fdbb4a_b.jpg"
        "09n" -> "https://i.pinimg.com/originals/fa/a0/e9/faa0e93a60455e42d8d1e7765581f8be.jpg"
        "10n" -> "https://i.pinimg.com/originals/fa/a0/e9/faa0e93a60455e42d8d1e7765581f8be.jpg"
        "11n" -> "https://www.ctvnews.ca/polopoly_fs/1.3520620.1501110555!/httpImage/image.jpg_gen/derivatives/landscape_620/image.jpg"
        "13n" -> "https://images.unsplash.com/photo-1454692173233-f4f34c12adad?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80"
        "50n" -> "https://1.bp.blogspot.com/_czJKXjlwzts/TDY2fyfVwvI/AAAAAAAAAAc/n21HsQxuf14/s1600/shanghai.jpg"

        else -> ""
    }
}