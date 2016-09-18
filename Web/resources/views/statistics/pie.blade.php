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
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-6" id="canvas-holder" >
            <canvas id="chart-area" width="500" height="500"></canvas>          
        </div>
        <div class="col-sm-4" style = " padding-top: 1%; font-size:18px;">
            <div id="js-legend" class="chart-legend"></div>         
        </div>
    </div>


@stop

