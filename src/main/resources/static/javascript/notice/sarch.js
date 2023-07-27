document.addEventListener("DOMContentLoaded", function () {
  // Get the search input element
  const searchInput = document.getElementById("search-input");

  // Attach a "keyup" event listener to the search input
  searchInput.addEventListener("keyup", function () {
    // Get the search query from the input field
    const searchQuery = searchInput.value.toLowerCase();

    // Filter the notice items based on the search query
    const filteredItems = storeditemss.filter((items) => {
      return items.title.toLowerCase().includes(searchQuery);
    });

    // Update the notice list with the filtered items
    showFilteredItems(filteredItems);
  });

  // Function to display the filtered items in the notice list
  function showFilteredItems(filteredItems) {
    // Clear the existing notice list
    NoticeListContainer.innerHTML = "";

    // Display the filtered items in the notice list
    filteredItems.forEach((items, index) => {
      // ... (your existing code to display the filtered items, same as in the showitemss() function)
    });
  }
});
