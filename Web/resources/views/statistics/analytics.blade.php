@extends('adminlte::page')

@section('title', 'CSeed')

@section('content_header')
    <div class="row" >
     <div class="col-sm-2"></div>
        <div class="col-sm-8">          
               <h1><center>Real Time Statistics</center></h1>
        </div>
        <div class="col-sm-2"></div>
    </div>
@stop

@section('content')

    <script src="js/Chart.js"></script>
    <link rel="stylesheet" href="css/box.css">
 
    <div class="row" >
     <div class="col-sm-2">
               <div class="info-box info-box-sm">
                    <span class="info-box-icon bg-blue"><span class="info-box-number">Particle</span></span>
                </div>
                <div class="info-box info-box-sm">
                    <span class="info-box-icon bg-green"><span class="info-box-number">Carbon</span></span>
                </div>
               <div class="info-box info-box-sm">
                    <span class="info-box-icon bg-red"><span class="info-box-number">Temp</span></span>
                </div>
                <div class="info-box info-box-sm">  
                     <span class="info-box-icon bg-orange"><span class="info-box-number">Humidity</span></span>
                </div>


     </div>
        <div class="col-sm-9">          
                <canvas id="line-graph" height="400" width="700"></canvas>
        </div>
        <div class="col-sm-1"></div>
    </div>

     <div class="row" >
     <div class="col-sm-3"></div>
        <div class="col-sm-8">          
               
   
        </div>
        <div class="col-sm-3"></div>
    </div>

    <!-- LINE GRAPH -->
       <script>
        var DataOut = {!! ($dataOut); !!};
        var particle = []; carbon = []; temp = []; humid = []; timec = [];

        DataOut.forEach(function(oItem, iIndex) {
            particle.push(oItem.particle);
            carbon.push(oItem.co2);
            temp.push(oItem.temperature);
            humid.push(oItem.humidity);
            timec.push(oItem.created_at);
        });


        var max = 10;
        var lineChartData = {
            labels : timec,
             datasets : [
                {
                    label: 'Particle Report',
                    fillColor : "rgba(0,102,204,0.2)",
                    strokeColor : "rgba(0,102,204,1)",
                    pointColor : "rgba(0,102,204,1)",
                    pointStrokeColor : "#004c99",
                    pointHighlightFill : "#004c99",
                    pointHighlightStroke : "#004c99",
                    data : particle
                },
                {
                    label: 'Carbon Report',
                    fillColor : "rgba(0,204,0,0.2)",
                    strokeColor : "rgba(0,204,0,1)",
                    pointColor : "rgba(0,204,0,1)",
                    pointStrokeColor : "#0066cc",
                    pointHighlightFill : "#0066cc",
                    pointHighlightStroke : "#0066cc",
                    data : carbon
                },
                {
                    label: 'Temperature Report',
                    fillColor : "rgba(204,0,0,0.2)",
                    strokeColor : "rgba(204,0,0,1)",
                    pointColor : "rgba(204,0,0,1)",
                    pointStrokeColor : "#FF3333",
                    pointHighlightFill : "#FF3333",
                    pointHighlightStroke : "#FF3333",
                    data : temp
                },
                {
                    label: 'Humidity Report',
                    fillColor : "rgba(204,102,0,0.2)",
                    strokeColor : "rgba(204,102,0,1)",
                    pointColor : "rgba(204,102,0,1)",
                    pointStrokeColor : "#ff9933",
                    pointHighlightFill : "#ff9933",
                    pointHighlightStroke : "#ff9933",
                    data : humid
                }
            ]

        }


        var line = document.getElementById("line-graph").getContext("2d");
        window.myLine = new Chart(line).Line(lineChartData, {
            scaleShowGridLines : true,
            scaleGridLineColor : "rgba(160,160,160,.4)",
            animation:true,
            scaleOverride:true,
            scaleSteps:max,
            scaleStartValue:5,
            scaleStepWidth:10
        });

        </script> 


@stop

