<?php 
mysql_connect("localhost","root","") or die();
mysql_select_db("mctrade") or die();

$tableName = "mctrade_trades";
$result = mysql_query("SELECT `id`, `Minecraft_Username`, `Block_Name`, `Quantity`, `Cost`, `Trade_Status` FROM $tableName");

$data = array();
while ( $row = mysql_fetch_assoc($result) )
{
    $data[] = $row;
	$output['aaData'][] = $row;
}
//$output['aaData'][] = $row;
header("Content-type: application/json");
echo json_encode( $output );    

?>