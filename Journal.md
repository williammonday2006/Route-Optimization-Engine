# Phase 1:

I chose an adjacency matrix because it makes checking connections and storing fuel costs easy. Since the network has a small, fixed number of cities, it is a simple approach.

# Phase 2:

BFS uses a Queue to visit nearby cities first before exploring farther connections. This allows it to explore the graph level-by-level.

# Phase 3:

BFS uses a Queue (First-In, First-Out) to explore nearby vertices first. DFS uses a Stack (Last-In, First-Out) to explore one path deeply before backtracking.

# Phase 4:

I tested connected and disconnected cities, a graph with only two cities, and searches starting and ending at the same city. Both BFS and DFS returned the expected results. I did not encounter any major bugs during testing.