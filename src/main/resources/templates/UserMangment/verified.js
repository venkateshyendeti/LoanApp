// ===== GLOBAL =====
let allUsers = [];
let deleteId = null;


// ===== LOAD USERS =====
async function loadUsers() {
    try {
        const token = localStorage.getItem("token");

        const response = await fetch("http://localhost:8080/create/verified", {
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        allUsers = await response.json();
        displayUsers();

    } catch (error) {
        console.error("Error:", error);
    }
}


// ===== DISPLAY USERS =====
function displayUsers() {
    const table = document.getElementById("userTable");
    const limit = document.getElementById("entriesSelect").value;

    table.innerHTML = "";

    const limitedUsers = allUsers.slice(0, limit);

    limitedUsers.forEach((user, index) => {
        table.innerHTML += `
            <tr>
                <td>${index + 1}</td>
                <td>${user.accountType}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td><span class="status">${user.accountStatus}</span></td>
                <td>
                    <div class="dropdown">
                        <button class="dropbtn" onclick="toggleDropdown(this)">Action ▼</button>
                        <div class="dropdown-content">
                            <a href="#" onclick="editUser(${user.id})">Edit</a>
                            <a href="#" onclick="viewUser(${user.id})">View</a>
                            <a href="#" onclick="deleteUser(${user.id})">Delete</a>
                        </div>
                    </div>
                </td>
            </tr>
        `;
    });
}


// ===== ENTRY CHANGE =====
document.getElementById("entriesSelect")
    .addEventListener("change", displayUsers);


// ===== EDIT =====
function editUser(id) {
    localStorage.setItem("editUserId", id);
    window.location.href = "update.html";
}


// ===== VIEW =====
function viewUser(id) {
    window.location.href = `view.html?id=${id}`;
}


// ===== DELETE (OPEN MODAL) =====
function deleteUser(id) {
    deleteId = id; // store id
    document.getElementById("deleteModal").style.display = "flex";
}


// ===== CONFIRM DELETE =====
async function confirmDelete() {
    try {
        const token = localStorage.getItem("token");

        console.log("Deleting ID:", deleteId);

        const response = await fetch(
            `http://localhost:8080/create/delete/${deleteId}`,
            {
                method: "DELETE",
                headers: {
                    "Authorization": "Bearer " + token
                }
            }
        );

        console.log("Status:", response.status);

        if (response.ok) {
            const msg = await response.text();
            alert("✅ " + msg);

            closeModal();
            loadUsers(); // refresh

        } else {
            const error = await response.text();
            alert("❌ " + error);
        }

    } catch (error) {
        console.error("Fetch Error:", error);
        alert("❌ Network Error");
    }
}


// ===== CLOSE MODAL =====
function closeModal() {
    document.getElementById("deleteModal").style.display = "none";
}


// ===== DROPDOWN =====
function toggleDropdown(btn) {
    document.querySelectorAll(".dropdown-content").forEach(d => {
        d.classList.remove("show");
    });

    const dropdown = btn.nextElementSibling;
    dropdown.classList.toggle("show");
}


// ===== CLICK OUTSIDE =====
window.onclick = function (event) {

    // close dropdown
    if (!event.target.matches('.dropbtn')) {
        document.querySelectorAll(".dropdown-content").forEach(d => {
            d.classList.remove("show");
        });
    }

    // close modal
    const modal = document.getElementById("deleteModal");
    if (event.target === modal) {
        closeModal();
    }
};


// ===== INIT =====
loadUsers();