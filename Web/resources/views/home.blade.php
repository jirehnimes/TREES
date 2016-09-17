@extends('adminlte::page')

@section('title', 'AdminLTE')

@section('content_header')
    <h1>Dashboard</h1>
@stop

@section('js')
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCSXAOugUemP6_eSQy_FzHM3Dm3k1ihWek"></script>
	<script src="js/home.js"></script>
@stop

@section('css')
	<link rel="stylesheet" href="css/home.css">
@stop

@section('content')
	<div class="row">
		<div class="col">
    		<div id="map"></div>
		</div>
	</div>
@stop

