// ✅ get userId from localStorage (set during edit click)
const userId = localStorage.getItem("editUserId");

// ✅ get token
const token = localStorage.getItem("token");


// ===== LOAD USER DATA INTO FORM =====
document.getElementById("updateForm").addEventListener("submit", async function(e) {
    e.preventDefault();

    const data = {
        accountType: document.getElementById("accountType").value,
        accountStatus: document.getElementById("accountStatus").value,
        firstName: document.getElementById("firstName").value,
        lastName: document.getElementById("lastName").value,
        email: document.getElementById("email").value,
        mobile: document.getElementById("mobileNumber").value, // ✅ FIXED
        enabled: document.getElementById("enabled").checked
    };

    console.log("Sending Data:", data); // 🔥 debug

    try {
        const response = await fetch(
            `http://localhost:8080/create/update/${userId}`,
            {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + token
                },
                body: JSON.stringify(data)
            }
        );

        if (response.ok) {
            alert("✅ Updated Successfully");
            window.location.href = "verified.html";
        } else {
            const error = await response.text();
            alert("❌ " + error);
        }

    } catch (error) {
        console.error("Update Error:", error);
    }
});

// 🔥 UPDATE USER
async function loadUser() {
    try {
        const response = await fetch(
            `http://localhost:8080/create/user/${userId}`, // your GET API
            {
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + token
                }
            }
        );

        const user = await response.json();

        console.log("User Data:", user);

        // ✅ Fill form fields
        document.getElementById("accountType").value = user.accountType;
        document.getElementById("accountStatus").value = user.accountStatus;
        document.getElementById("firstName").value = user.firstName || "";
        document.getElementById("lastName").value = user.lastName || "";
        document.getElementById("email").value = user.email || "";
        document.getElementById("mobileNumber").value = user.mobileNumber || "";
        document.getElementById("enabled").checked = user.enabled;

    } catch (error) {
        console.error("Error loading user:", error);
    }
}

   // call load on page start
   loadUser();