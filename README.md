# ImageFinderApp

Fun things about this project:
1. Hilt for DI
2. Compose for UI with reusable components
3. MVVM architecture
4. Coil for image loading
5. Lifecycle aware state flow which retains state upon orientation change and back navigation
6. Custom text field / search to show custom styl with boarder, vector icons, and animated visibility on clear icon button
7. Scrolling header on search page and scrolling content on detail page
8. Dynamic number of image columns based upon orientation 
9. Uses retrofit and pexels api to get images
10. Demonstration of coroutines with suspend functions in image repository and within viewModel scope
11. Each screen has loading state, error state, and main content, animated visibility
12. One activity and multiple fragments, bundle to pass image id from search screen to detail screen
13. Compose navigation
14. Auto hide soft keyboard when hitting search button
15. Auto hide search button when loading
16. Sealed class of network result
17. Splash icon
18. View binding
19. Unit tests
20. View model tests

High quality screen recording here: https://drive.google.com/drive/folders/1p1-lH9LdhBFY06BxGJVWnxWdhO8vrJWH?usp=sharing


![Screen Recording 2023-12-08 at 5 19 46 PM](https://github.com/jakemharris/ImageFinderApp/assets/6319286/69b28e54-00bf-44c0-8dd3-5b336d99b3e2)

Unit test coverage report over 80% line coverage:

<img width="585" alt="Screenshot 2023-12-17 at 1 34 28 PM" src="https://github.com/jakemharris/ImageFinderApp/assets/6319286/ed517707-85db-4efc-874a-71e30748e6e9">
