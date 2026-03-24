// ✅ get userId from localStorage (set during edit click)
const userId = localStorage.getItem("editUserId");

// ✅ get token
const token = localStorage.getItem("token");


// ===== LOAD USER DATA INTO FORM =====
async function loadUser() {
    try {
        const response = await fetch(`http://localhost:8080/create/user/${userId}`, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        const user = await response.json();

        // set values to form
        document.getElementById("accountType").value = user.accountType;
        document.getElementById("accountStatus").value = user.accountStatus;
        document.getElementById("firstName").value = user.firstName;
        document.getElementById("lastName").value = user.lastName;
        document.getElementById("email").value = user.email;
        document.getElementById("mobile").value = user.mobileNumber;
        document.getElementById("enabled").checked = user.enabled;

    } catch (error) {
        console.error("Load Error:", error);
    }
}

// 🔥 UPDATE USER
document.getElementById("updateForm").addEventListener("submit", async function(e) {
    e.preventDefault();

    const data = {
        accountType: document.getElementById("accountType").value,
        accountStatus: document.getElementById("accountStatus").value,
        firstName: document.getElementById("firstName").value,
        lastName: document.getElementById("lastName").value,
        email: document.getElementById("email").value,
        mobile: document.getElementById("mobile").value,
      // password: document.getElementById("password").value,
        //confirmPassword: document.getElementById("confirmPassword").value,
        enabled: document.getElementById("enabled").checked
    };

    try {
           const response = await fetch(`http://localhost:8080/create/update/${userId}`, {
               method: "PUT",
               headers: {
                   "Content-Type": "application/json",
                   "Authorization": "Bearer " + token
               },
               body: JSON.stringify(data)
           });

           if (response.ok) {
               const msg = await response.text();
               alert("✅ " + msg);

               // redirect back to list
               window.location.href = "verified.html";

           } else {
               const error = await response.text();
               alert("❌ Failed: " + error);
           }

       } catch (error) {
           console.error("Update Error:", error);
           alert("❌ Server Error");
       }
   });


   // call load on page start
   loadUser();