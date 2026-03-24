document.addEventListener("DOMContentLoaded", async function () {

    // get id from URL
    const params = new URLSearchParams(window.location.search);
    const userId = params.get("id");

    try {
        const response = await fetch(`http://localhost:8080/create/user/${userId}`);
        const user = await response.json();

        document.getElementById("accountType").value = user.accountType;
        document.getElementById("accountStatus").value = user.accountStatus;
        document.getElementById("firstName").value = user.firstName;
        document.getElementById("lastName").value = user.lastName;
        document.getElementById("email").value = user.email;
        document.getElementById("mobile").value = user.mobileNumber;
       // document.getElementById("enabled").checked = user.enabled;

    } catch (error) {
        console.error("Error loading user:", error);
    }
});

function goBack() {
    window.location.href = "verified.html";
}