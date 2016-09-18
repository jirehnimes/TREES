@extends('adminlte::page')

@section('title', 'CSeed')

@section('content_header')
    <div class="row" >
     <div class="col-sm-2"></div>
        <div class="col-sm-8">          
               <h1><center>Data Reports</center></h1>
        </div>
        <div class="col-sm-2"></div>
    </div>
@stop

   

@section('content')
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #E0E0E0;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #E0E0E0;
}
</style>
<table>

  <tr>
    <th>Phone Number</th>
    <th>Latitude</th>
    <th>Longitude</th>
    <th>Particle</th>
    <th>Carbon</th>
    <th>Temperature</th>
    <th>Humidity</th>
    <th>Date Added</th>
  </tr>


@foreach($sensor_data as $sensor_data)
    <tr>
        <td> {{$sensor_data->phone_number}}</td>
        <td> {{$sensor_data->x_coordinate}}</td>
        <td> {{$sensor_data->y_coordinate}}</td>
        <td> {{$sensor_data->particle}}</td>
        <td> {{$sensor_data->co2}}</td>
        <td> {{$sensor_data->temperature}}</td>
        <td> {{$sensor_data->humidity}}</td>
        <td> {{$sensor_data->created_at}}</td>
    </tr>  
@endforeach


</table>

@stop