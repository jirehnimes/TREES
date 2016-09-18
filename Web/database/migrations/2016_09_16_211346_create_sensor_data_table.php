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
            // $table->string('phone_number')->unique();
            $table->string('phone_number');
            $table->decimal('x_coordinate', 13, 6);
            $table->decimal('y_coordinate', 13, 6);
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
