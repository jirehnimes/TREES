<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;

class ReportsController extends Controller
{
     public function display(){   
  //        $recordCount=0;
     $sensor_data = \DB::table('sensor_data')->select('phone_number','x_coordinate','y_coordinate','particle','co2','temperature','humidity','created_at')->orderBy('created_at', 'desc')->get();
         
  //        $jTableResult = array();
    // $jTableResult['Result'] = "OK";
    // $jTableResult['TotalRecordCount'] = 4;
    // $jTableResult['Records'] = $sensor_data;
    // print json_encode($jTableResult);
      return view('statistics.reports',['sensor_data' => $sensor_data]);
    }
}
