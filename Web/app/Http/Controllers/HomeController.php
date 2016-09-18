<?php

namespace App\Http\Controllers;

use App\Http\Requests;
use Illuminate\Http\Request;

use App\SensorDatum;
use App\Tree;

class HomeController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware('auth');
    }

    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $aSensorData = SensorDatum::all();
        $aTrees = Tree::all();
        $aData = array(
            'aSensorData' => $aSensorData,
            'aTrees' => $aTrees
        );

        return view('home', $aData);
    }
}
