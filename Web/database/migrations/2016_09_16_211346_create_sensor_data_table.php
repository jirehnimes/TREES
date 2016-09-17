<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateSensorDataTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('sensor_data', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('company_id');
            $table->integer('phone_number')->unique();
            $table->float('x_coordinate');
            $table->float('y_coordinate');
            $table->float('particle');
            $table->float('co2');
            $table->float('temperature');
            $table->float('humidity');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::drop('sensor_data');
    }
}
