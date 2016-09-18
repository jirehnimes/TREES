@extends('adminlte::page')

@section('title', 'AdminLTE')

@section('content_header')
    <h1>Dashboard</h1>
@stop

@section('js')
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCSXAOugUemP6_eSQy_FzHM3Dm3k1ihWek"></script>
	<script src="js/googleMapsApi.js"></script>
	<script src="js/home.js"></script>
	<script type="text/javascript">
		var aSensorData = {!! json_encode($aSensorData) !!};
		var aTrees = {!! json_encode($aTrees) !!};
	</script>
@stop

@section('css')
	<link rel="stylesheet" href="css/home.css">
@stop

@section('content')
	<div class="row info-column">
		<div class="col col-sm-4">
			<div class="box box-widget widget-user">
				<div class="widget-user-header bg-green-active">
					<div class="row" style="padding: 0;">
						<div class="col-sm-5">
							<p class="widget-user-region">Montalban</p>
							<p class="widget-user-province">Rizal Province</p>
						</div>
						<div class="col-sm-7 bg-white">
							<p class="widget-user-area"></p>
						</div>
					</div>
				</div>
				<div class="box-footer">
					<div class="row">
						<div class="col-sm-12">
							<div class="box box-default collapsed-box">
								<div class="box-header with-border bg-green-active">
									<h3 class="box-title">List of Involved Companies</h3>
									<div class="box-tools pull-right">
										<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
									</div>
								</div>
								<div class="box-body">
									<p class="text-green">Company 1</p> <br>
									<p class="text-green">Company 2</p> <br>
									<p class="text-green">Company 3</p> <br>
									<p class="text-green">Company 4</p> <br>
									<p class="text-green">Company 5</p> <br>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12 border-right">
			  				<div class="description-block">
			    				<div class="info-box">
									<span class="info-box-icon bg-red"><i class="fa fa-asterisk"></i></span>
									<div class="info-box-content particle-info">
										<span class="info-box-text">Particle</span>
										<span class="info-box-number">0</span>
									</div>
								</div>
			  				</div>
						</div>
						<div class="col-sm-12 border-right">
			  				<div class="description-block">
			    				<div class="info-box">
									<span class="info-box-icon bg-green"><i class="fa fa-cloud"></i></span>
									<div class="info-box-content co2-info">
										<span class="info-box-text">Carbon Dioxide</span>
										<span class="info-box-number">0</span>
									</div>
								</div>
			  				</div>
						</div>
						<div class="col-sm-12 border-right">
			  				<div class="description-block">
			    				<div class="info-box">
									<span class="info-box-icon bg-yellow"><i class="fa fa-sun-o"></i></span>
									<div class="info-box-content temperature-info">
										<span class="info-box-text">Temperature</span>
										<span class="info-box-number">0</span>
									</div>
								</div>
			  				</div>
						</div>
						<div class="col-sm-12 border-right">
			  				<div class="description-block">
			    				<div class="info-box">
									<span class="info-box-icon bg-blue"><i class="fa fa-tint"></i></span>
									<div class="info-box-content humidity-info">
										<span class="info-box-text">Humidity</span>
										<span class="info-box-number">0</span>
									</div>
								</div>
			  				</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col col-sm-8">
    		<div id="map"></div>
		</div>
	</div>
@stop

