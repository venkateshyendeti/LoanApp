document.addEventListener("DOMContentLoaded", function () {

    const form = document.querySelector("form");

    form.addEventListener("submit", async function (e) {
        e.preventDefault(); // stop default submit

        // collect form data
        const formData = new FormData(form);

        const data = {
            accountType: formData.get("accountType"),
            accountStatus: formData.get("accountStatus"),
            firstName: formData.get("firstName"),
            lastName: formData.get("lastName"),
            email: formData.get("email"),
            mobile: formData.get("mobile"),
            password: formData.get("password"),
            confirmPassword: formData.get("confirmPassword"),
            enabled: formData.get("enabled") === "true"
        };

        try {
            const response = await fetch("http://localhost:8080/create/add", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            });

            if (response.ok) {
                alert("✅ User registered successfully");

                // 🔥 redirect to dashboard
                window.location.href = "../dashboard.html";
            } else {
                const error = await response.text();
                alert("❌ Failed: " + error);
            }

        } catch (err) {
            alert("❌ Error: " + err);
        }
    });

});