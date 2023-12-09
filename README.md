# ImageFinderApp

Fun things about this project:
1. Hilt for DI
2. Compose for UI with reusable components
3. MVVM archetecture
4. Coil for image loading
5. Lifecycle aware state flow which retains state upon orientation change and back navigation
6. Custom text field / search to show custom styl with boarder, vector icons, and animated visibility on clear icon button
7. Scrolling header on search page and scrolling content on detail page
8. Dynamic number of image columns based upon orientation 
9. Uses retrofit and pexels api to get images
10. Demonstration of coroutines with suspend functions in image repository and within viewModel scope
11. Each screen has loading state, error state, and main content, animated visibility
12. One activity and multiple fragments, bundle to pass image id from search screen to detail screen
13. Cool set up to handle events for navigation so viewModels don't have context
14. Auto hide soft keyboard when hitting search button
15. Auto hide search button when loading
16. Sealed class of network result
17. Splash icon
18. View binding
19. Nav graph
20. Unit tests
21. View model tests