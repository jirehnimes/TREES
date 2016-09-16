<?php

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

use App\User;

class UsersSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('users')->delete();
 
        User::create(array(
            'first_name' => 'Admin',
            'last_name' => 'Admin',
            'email' => 'admin@cseed.com',
            'password' => Hash::make('password'),
        ));
    }
}
