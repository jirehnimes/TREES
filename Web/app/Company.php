<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Company extends Model
{
    /**
     * Get the data of sensor of a company.
     */
    public function sensorData()
    {
        return $this->hasMany('App\SensorDatum');
    }
}
