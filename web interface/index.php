<?php

require 'Slim/Slim.php';
\Slim\Slim::registerAutoloader();
require 'Config.php';
require 'Database.class.php';

$app = new \Slim\Slim();

$app->get('/', function() {
   echo "'sup?";
});

$app->post('/locations', function(){
    $db = Database::getInstance();
    
    $sql = "INSERT INTO locations
                (code, longitude, latitude)
            VALUES
                (:code, :long, :lat)";
    
    $query = $db->connection->prepare($sql);
    
    $params = array(
        "code" => $_POST['code'],
        "long" => $_POST['long'],
        "lat" => $_POST['lat']
    );
    
    $result = array();
    $result['status'] = "";
    $result['error'] = "";
    
    try {
        $ps->execute($params);
    
        $result['status'] = "OK";
    } catch (PDOException $e) {
        $result['status'] = 'Error';
        $result['error'] = $e->getMessage();
    }
    
    header('Content-type: application/json');
    echo json_encode($result);
    
});

$app->get('/locations/:code', function($code){
    
    $db = Database::getInstance();
    
    $sql = "SELECT * FROM locations WHERE code = :code";
    $query = $db->connection->prepare($sql);
    
    $params = array(
            "code" => $code
    );
    
    $loc['status'] = "";
    $loc['error'] = "";
    $loc['locations'] = array();
    
    try {
        $query->execute($params);
        
        if ($query->rowCount() < 1) {
            $loc['status'] = "Error";
            $loc['error'] = "No locations.";
        } else {
    
            $query->setFetchMode(PDO::FETCH_ASSOC);
            $loc['status'] = "OK";
            $i = 0;
            foreach ($query as $row){
                foreach ($row as $field => $value){
                    $loc['locations'][$i][$field] = $value;
                }
                $i++;
            }
        }
        
    } catch (PDOException $e) {
        $loc['status'] = "Error";
        $loc['error'] = $e->getMessage();
    }
    
    header('Content-type: application/json');
    echo json_encode($loc);
    
});

$app->post('/comments', function(){
    $db = Database::getInstance();
    
    $sql = "INSERT INTO comments
                (code, name, location, comment, date)
            VALUES
                (:code, :name, :loc, :comment, :date)";
    
    $query = $db->connection->prepare($sql);
    
    $params = array(
        "code" => $_POST['code'],
        "name" => $_POST['name'],
        "loc" => $_POST['loc'],
        "comment" => $_POST['comment'],
        "date" => date("Y-m-d H:i:s")
    );
        
    $result = array();
    $result['status'] = "";
    $result['error'] = "";
    
    try {
        $ps->execute($params);
    
        $result['status'] = "OK";
    } catch (PDOException $e) {
        $result['status'] = 'Error';
        $result['error'] = $e->getMessage();
    }
    
    header('Content-type: application/json');
    echo json_encode($result);
    
});

$app->get('/comments/:code', function($code){
    
    $db = Database::getInstance();
    
    $sql = "SELECT * FROM comments WHERE code = :code";
    $query = $db->connection->prepare($sql);
    
    $params = array(
            "code" => $code
    );
    
    $comments = array();
    $comments['status'] = "";
    $comments['error'] = "";
    $comments['num_comments'] = "";
    $comments['comments'] = array();
    
    try {
        $query->execute($params);
        
    
        if ($query->rowCount() < 1) {
            $product['status'] = "Error";
            $product['error'] = "No comments.";
        } else {
            
            $query->setFetchMode(PDO::FETCH_ASSOC);
            $comments['status'] = "OK";
            $i = 0;
            
            foreach ($query as $row){
                
                foreach ($row as $field => $value){
                    $comments['comments'][$i][$field] = $value;
                }
                $i++;
            }
        }
    } catch (PDOException $e) {
        $comments['status'] = "Error";
        $comments['error'] = $e->getMessage();
    }
    
    header('Content-type: application/json');
    echo json_encode($comments);
    
});

$app->post('/product', function(){
    $db = Database::getInstance();
    
    $sql = "INSERT INTO products 
                (code, name, description, rating, num_votes, views) 
            VALUES
                (:code, :name, :desc, :rating, :num_votes, :views)";
    
    $query = $db->connection->prepare($sql);
        
    $params = array(
            "code" => $_POST['code'],
            "name" => $_POST['name'],
            "desc" => $_POST['desc'],
            "rating" => $_POST['rating'],
            "num_votes" => 1,
            "views" => 1
    );
    
    $result = array();
    $result['status'] = "";
    $result['error'] = "";
    
    try {
        $ps->execute($params);
        
        $result['status'] = "OK";
    } catch (PDOException $e) {
        $result['status'] = 'Error';
        $result['error'] = $e->getMessage();
    }
    
    header('Content-type: application/json');
    echo json_encode($result);
    
});

$app->get('/product', function(){
  // nothing here  
});

$app->get('/product/:code', function($code){
    
    $db = Database::getInstance();
    
    $sql = "SELECT * FROM products WHERE code = :code LIMIT 1";
    $query = $db->connection->prepare($sql);
    
    $params = array(
            "code" => $code
    );
    
    $product = array();
    
    $product['status'] = "";
    $product['error'] = "";
    $product['code'] = "";
    $product['image'] = "";
    $product['description'] = "";
    $product['rating'] = "";
    $product['num_votes'] = "";
    $product['views'] = "";
    $product['status'] = "";
    $product['error'] = "";
        
    try {
        $query->execute($params);
        
        if ($query->rowCount() < 1) {
            $product['status'] = "Error";
            $product['error'] = "Product not found";
        } else {
            $product['status'] = "OK";
            $query->setFetchMode(PDO::FETCH_ASSOC);
            $row = $query->fetch();
            
            foreach ($row as $field => $value) {
                $product[$field] = $value;
            }
        }
    } catch (PDOException $e) {
            $product['status'] = "Error";
            $product['error'] = $e->getMessage();
    }
    
    header('Content-type: application/json');
    echo json_encode($product);
    
});


$app->run();