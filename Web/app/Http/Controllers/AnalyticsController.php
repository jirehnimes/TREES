<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;

class AnalyticsController extends Controller
{
        public function show(){     
        $sensor_data = \DB::table('sensor_data')->select('particle','co2','temperature','humidity', 'created_at')->orderBy('created_at', 'asc')->limit(20)->get();
        $dataOut = json_encode($sensor_data);
        return view('statistics.analytics',['dataOut' => $dataOut]);
    }

}
