<?php 
//mysql_connect("localhost","root","") or die();
//mysql_select_db("mctrade") or die();

mysql_connect("mysql10.000webhost.com","a1394881_fog","123987justin") or die();
mysql_select_db("a1394881_db") or die();

$tableName = "mctrade_trades";
$result = mysql_query("SELECT `id`, `Minecraft_Username`, `Block_Name`, `Quantity`, `Cost`, `Trade_Status` FROM $tableName");

$data = array();
while ( $row = mysql_fetch_assoc($result) )
{
    $data[] = $row;
}
header("Content-type: application/json");
echo json_encode( $data );    

?>