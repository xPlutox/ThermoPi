$(document).ready(function () {
    function fetchRoomStatus() {
        console.log("Fetching metrics");
        $.ajax({
            url: "api/thermostat/room/get",
            type: "GET",
            dataType: "json",
            beforeSend: function(xhr, settings) {
                //$("span#temp, span#humid").html("Reloading...");
            },
            success: function (json) {
                if (true == json.heatingStatus.enabled) {
                    $("span#heatingStatus").html("enabled");
                } else {
                    $("span#heatingStatus").html("disabled");
                }
                $("span#temp").html(json.metrics.temperature);
                $("span#humid").html(json.metrics.humidity);
                setTimeout(fetchRoomStatus, 5000);

                if ($("#slider").roundSlider("option", "disabled") == true) {
                    $("#slider").roundSlider("enable");
                    $("#slider").roundSlider("option", {value: json.targetTemperature});
                }
            },
            error: function (xhr, status, errorThrown) {
                $("span#temp, span#humid").html("Oops, problem while fetching...");
                console.log("Metrics Fetch Error: " + errorThrown);
                console.log("Status: " + status);
                console.dir(xhr);
            },
            complete: function (xhr, status) {
            }
        });
    }

    $("#heatingToggle").click(function () {
        console.log("Toggle heating on/off");
        $.ajax({
            url: "api/heating/toggle",
            type: "GET",
            dataType: "json",
            beforeSend: function() {
                $("#slider").roundSlider("disable");
            },
            success: function (json) {
                $("#slider").roundSlider("enable");
            },
            error: function (xhr, status, errorThrown) {
                console.log("Toggle Error: " + errorThrown);
                console.log("Status: " + status);
                console.dir(xhr);
            },
            complete: function (xhr, status) {
            }
        });
    });

    function changeTargetTemp(e) {
        console.log("Change target temp to: " + e.value);
        $.ajax({
            url: "api/thermostat/target/set",
            data: {
                target: e.value
            },
            type: "GET",
            dataType: "json",
            success: function (json) {
                console.log("target temp changed");
            },
            error: function (xhr, status, errorThrown) {
                console.log("Toggle Error: " + errorThrown);
                console.log("Status: " + status);
                // console.dir(xhr);
            },
            complete: function (xhr, status) {

            }
        });
    }

    fetchRoomStatus();

    $("#slider").roundSlider({
        change: changeTargetTemp,
        sliderType: "min-range",
        handleShape: "round",
        disabled: true,
        width: 20,
        radius: "100",
        value: 1,
        handleSize: "+1",
        step: "0.20",
        min: "1",
        max: "30"
    });
});