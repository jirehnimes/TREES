<?php

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

use App\SensorDatum;

class SensorDataSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('sensor_data')->delete();
 
        $aData = array(
        	array(
	            'company_id' => '1010',
	            'phone_number' => '+63906000000',
	            'x_coordinate' => 14.798600,
	            'y_coordinate' => 121.196200,
	            'particle' => 0.11,
	            'co2' => 35.5,
	            'temperature' => 34.5,
	            'humidity' => 54.1,
	        ),
	        array(
	            'company_id' => '1011',
	            'phone_number' => '+63906000001',
	            'x_coordinate' => 14.749600,
	            'y_coordinate' => 121.186100,
	            'particle' => 0.15,
	            'co2' => 36.2,
	            'temperature' => 34.5,
	            'humidity' => 54.2,
	        ),
	        array(
	            'company_id' => '1012',
	            'phone_number' => '+63906000002',
	            'x_coordinate' => 14.792800,
	            'y_coordinate' => 121.150400,
	            'particle' => 0.2,
	            'co2' => 38.1,
	            'temperature' => 35.1,
	            'humidity' => 55.6,
	        ),
	        array(
	            'company_id' => '1013',
	            'phone_number' => '+63906000003',
	            'x_coordinate' => 14.784500,
	            'y_coordinate' => 121.114700,
	            'particle' => 0.5,
	            'co2' => 40.0,
	            'temperature' => 33.2,
	            'humidity' => 57.6,
	        ),
	        array(
	            'company_id' => '1014',
	            'phone_number' => '+63906000004',
	            'x_coordinate' => 14.792800,
	            'y_coordinate' => 121.150400,
	            'particle' => 0.3,
	            'co2' => 38.1,
	            'temperature' => 31.0,
	            'humidity' => 52.3,
	        ),
	        array(
	            'company_id' => '1015',
	            'phone_number' => '+63906000005',
	            'x_coordinate' => 14.766600,
	            'y_coordinate' => 121.135600,
	            'particle' => 0.5,
	            'co2' => 40.1,
	            'temperature' => 29.0,
	            'humidity' => 50.3,
	        ),
	        array(
	            'company_id' => '1016',
	            'phone_number' => '+63906000006',
	            'x_coordinate' => 14.776200,
	            'y_coordinate' => 121.158600,
	            'particle' => 0.4,
	            'co2' => 40.3,
	            'temperature' => 27.0,
	            'humidity' => 51.5,
	        ),
	        array(
	            'company_id' => '1017',
	            'phone_number' => '+63906000007',
	            'x_coordinate' => 14.780200,
	            'y_coordinate' => 121.146600,
	            'particle' => 0.2,
	            'co2' => 45.2,
	            'temperature' => 30.0,
	            'humidity' => 53.3,
	        ),
	        array(
	            'company_id' => '1018',
	            'phone_number' => '+63906000008',
	            'x_coordinate' => 14.766300,
	            'y_coordinate' => 121.150000,
	            'particle' => 0.4,
	            'co2' => 45.2,
	            'temperature' => 30.0,
	            'humidity' => 55.3,
	        ),
	        array(
	            'company_id' => '1019',
	            'phone_number' => '+63906000009',
	            'x_coordinate' => 14.790500,
	            'y_coordinate' => 121.226600,
	            'particle' => 0.4,
	            'co2' => 45.2,
	            'temperature' => 30.0,
	            'humidity' => 55.3,
	        )
    	);

        foreach ($aData as $iKey => $aValue) {
        	SensorDatum::create($aValue);
        }
    }
}
