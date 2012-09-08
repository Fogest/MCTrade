<?php 
mysql_connect("localhost","root","") or die();
mysql_select_db("mctrade") or die();

//mysql_connect("mysql11.000webhost.com","a6736476_fogest","123987justin") or die();
//mysql_select_db("a6736476_mctrade") or die();

$tableName = "itemlist";
$result = mysql_query("SELECT * FROM $tableName");

$data = array();
while ( $row = mysql_fetch_assoc($result) )
{
    $data[] = $row;
}
header("Content-type: application/json");
echo json_encode( $data );    

?>