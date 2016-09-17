<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class SensorDatum extends Model
{
    /**
     * Get the company of this sensor data.
     */
    public function company()
    {
        return $this->belongsTo('App\Company', 'company_id');
    }
}
