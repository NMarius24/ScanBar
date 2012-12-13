<?php

/**
 * Connect to a database using PDO
 *
 * @name    Database
 * @author  Dejan Angelov
 */

class Database {

    public $connection;
    private static $instance;

    private function __construct() {
        $host = Config::$db_host;
        $db	  = Config::$db_name;
        $user = Config::$db_username;
        $pass = Config::$db_password;

        $dsn = "mysql:host=". $host .";dbname=". $db;

        $opt = array(
                // any occurring errors wil be thrown as PDOException
                PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
                // an SQL command to execute when connecting
                PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES 'UTF8'"
        );

        try {
            $this->connection = new PDO($dsn, $user, $pass, $opt);
        } catch (PDOException $e) {
            throw $e;
        }
    }

    public static function getInstance() {
        if (!isset(self::$instance)) {
            $class = __CLASS__;
            self::$instance = new $class();
        }

        return self::$instance;
    }

}