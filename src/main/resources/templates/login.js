async function loginUser(event) {
    event.preventDefault();

    let email = document.querySelector("input[type='email']").value;
    let password = document.querySelector("input[type='password']").value;

    try {
        let response = await fetch("http://localhost:8080/auth/api/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email: email,
                password: password
            })
        });

        if (response.ok) {
            let data = await response.json();

            // 👉 save token (if JWT)
           localStorage.setItem("token", data.accessToken);
           localStorage.setItem("refreshToken", data.refreshToken);

            alert("Login Success");

            // 👉 redirect
            window.location.href = "dashboard.html";

        } else {
            alert("Invalid Credentials");
        }

    } catch (error) {
        console.error(error);
        alert("Server Error");
    }
}