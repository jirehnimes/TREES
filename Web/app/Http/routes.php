<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

// Web Routes

Route::auth();
Route::get('/', 'HomeController@index');
// Route::get('/home', 'HomeController@index');

// Route::group(['namespace' => 'Web'], function()
// {
// });

// API Routes

Route::group(['prefix' => 'api'], function () {

    Route::get('users', 'UsersController@index');
    Route::get('users/{id}', 'UsersController@show');

    Route::post('sensor', 'SensorDataController@store');

});
