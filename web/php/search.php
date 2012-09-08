<?php 
/** MySQL database name */
define('DB_NAME', 'a6736476_mctrade');

/** MySQL database username */
define('DB_USER', 'a6736476_fogest');

/** MySQL database password */
define('DB_PASSWORD', '123987justin');

/** MySQL hostname */
define('DB_HOST', 'mysql11.000webhost.com');

$mysql_host = "mysql11.000webhost.com";
$mysql_database = "a6736476_mctrade";
$mysql_user = "a6736476_fogest";
$mysql_password = "123987justin";


$con = mysql_connect($mysql_host,$mysql_user,$mysql_password);
if(!$con) {
	die('Could not connect: ' . mysql_error());
}
$dbs = mysql_select_db($mysql_database);
if(!$dbs) {
	die('Can\'t use ' . DB_NAME . ': ' . mysql_error());
}

/**
$tableName = "itemlist";
$result = mysql_query("SELECT * FROM $tableName");

$data = array();
while ( $row = mysql_fetch_assoc($result) )
{
    $data[] = $row;
}
header("Content-type: application/json");
echo json_encode( $data );    
*/
echo 'No point searching. This is not functional yet!';

?>