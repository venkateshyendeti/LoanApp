// ===== GLOBAL =====
let allUsers = [];
let deleteId = null;
let currentPage = 0;
let totalPages = 0;
let searchText = "";


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

    table.innerHTML = "";

    allUsers.forEach((user, index) => {

        let firstName = user.firstName || "-";
        let lastName = user.lastName || "-";

        table.innerHTML += `
            <tr>
                <td>${index + 1 + (currentPage * 10)}</td>
                <td>${user.accountType || "User"}</td>
                <td>${firstName}</td>
                <td>${lastName}</td>
                <td>${user.email ? user.email : "N/A"}</td>
                <td><span class="status">${user.accountStatus || "verified"}</span></td>
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
//loadUsers();

   async function loadUsers(page = 0) {
       try {
           const token = localStorage.getItem("token");
           const size = document.getElementById("entriesSelect").value;

           console.log("Loading page:", page);

          const response = await fetch(
              `http://localhost:8080/create/users?status=verified&search=${searchText}&page=${page}&size=${size}`,
               {
                   headers: {
                       "Authorization": "Bearer " + token
                   }
               }
           );

           const data = await response.json();

           console.log("API RESPONSE:", data);
           console.log("Current Page:", currentPage);
           console.log("Total Pages:", totalPages);

           allUsers = data.users;
           currentPage = data.currentPage;
           totalPages = data.totalPages;

           displayUsers();

           document.getElementById("prevBtn").disabled = currentPage === 0;
           document.getElementById("nextBtn").disabled = currentPage === totalPages - 1;

       } catch (error) {
           console.error("Error:", error);
       }
   }
   loadUsers(0);

    function nextPage() {
        if (currentPage < totalPages - 1) {
            loadUsers(currentPage + 1);
        }
    }

    function prevPage() {
        if (currentPage > 0) {
            loadUsers(currentPage - 1);
        }
    }


    document.getElementById("entriesSelect")
        .addEventListener("change", () => loadUsers(0));



        let debounceTimer;

        document.getElementById("searchInput").addEventListener("input", function () {

            clearTimeout(debounceTimer);

            debounceTimer = setTimeout(() => {
                searchText = this.value;
                loadUsers(0);
            }, 400);
        });


