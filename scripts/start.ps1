# Define the jar file name
$toolVersion = "0.1.2"
$jarFileName = "X4CustomFaction-$toolVersion.jar"
$requiredJavaVersion = 21

# Check if Java is installed
$javaVersion = & java -version 2>&1
if ($javaVersion[0] -match 'version "(\d+)\.')
{
    $majorVersion = [int]$matches[1]

    # Check if the Java version is 21 or more
    if ($majorVersion -ge $requiredJavaVersion)
    {
        Write-Output "Java version $majorVersion is installed."
        # Start the jar file
        Write-Output "Starting the application..."
        java -jar $jarFileName
    }
    else
    {
        Write-Output "Java version $requiredJavaVersion or higher is required. Current version is $majorVersion"
        Write-Output "Please install Java version $requiredJavaVersion or higher. See: https://www.oracle.com/java/technologies/downloads/"
    }
}
else
{
    Write-Output "Java is not installed or the version could not be determined."
    Write-Output "Please install Java version $requiredJavaVersion or higher. See: https://www.oracle.com/java/technologies/downloads/"
}
Pause