

$("#startQuizButton").click(function () {
    var timer2 = "5:01";
    var interval = setInterval(function () {


        var timer = timer2.split(':');
        //by parsing integer, I avoid all extra string processing
        var minutes = parseInt(timer[0], 10);
        var seconds = parseInt(timer[1], 10);
        --seconds;
        minutes = (seconds < 0) ? --minutes : minutes;
        if (minutes < 0) clearInterval(interval);
        seconds = (seconds < 0) ? 59 : seconds;
        seconds = (seconds < 10) ? '0' + seconds : seconds;
        //minutes = (minutes < 10) ?  minutes : minutes;
        $('#timer').html(minutes + 'm:' + seconds + "s");
        timer2 = minutes + 'm:' + seconds + "s";
    }, 1000);
    $("#StartDiv").css("display", "none")
    $("#quessionDiv").css("filter", "blur(0px)")
    $("#quessionDiv2").css("filter", "blur(0px)")
})
