<?php 
$senderName = "";
$senderEmail = "";
$senderMessage = "";

if ($_POST['name']) { // If the form is trying to post value of username field
 
    // Gather the posted form variables into local PHP variables
    $senderName = $_POST['name'];
    $senderEmail = $_POST['email'];
	$senderSubject = $_POST['subject'];
    $senderMessage = $_POST['message'];
	$senderIP = $_SERVER['REMOTE_ADDR'];


        // Run any filtering here
        $senderName = strip_tags($senderName);
        $senderName = stripslashes($senderName);
        $senderEmail = strip_tags($senderEmail);
        $senderEmail = stripslashes($senderEmail);
        $senderMessage = strip_tags($senderMessage);
        $senderMessage = stripslashes($senderMessage);
        // End Filtering
        $to = "fogestjv@gmail.com";         
        $from = "justin@fogest.net16.net";
        $subject = "Contact Form Message (MCTrade) - $senderSubject";
        // Begin Email Message Body
        $message = "
		Message:
        $senderMessage
		Name:
        $senderName
		Email:
        $senderEmail
		IP:
		$senderIP
        ";
        // Set headers configurations
        $headers  = 'MIME-Version: 1.0' . "/r/n";
        $headers .= "Content-type: textrn";
        $headers .= "From: $fromrn";
        // Mail it now using PHP's mail function
        mail($to, $subject, $message, $headers);
        $formMessage = "Thanks, your message has been sent.";
        $senderName = "";
        $senderEmail = "";
        $senderMessage = "";
} // close if (POST condition
echo "Your email has been sent! You will be redirected in about 3 seconds";
header('Refresh:5; \mctrade/index.html'); 
exit();
?>