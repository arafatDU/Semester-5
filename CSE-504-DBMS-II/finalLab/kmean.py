import random
import math
import matplotlib.pyplot as plt

# Sample dataset (2D points)
data = [[1, 2], [1, 4], [1, 0], [4, 2], [4, 4], [4, 0]]

# Number of clusters (K)
K = 2  

# 1️⃣ Initialize Centroids (Choose Random Points)
centroids = random.sample(data, K)
print(f"Initial Centroids: {centroids}")

# Function to calculate Euclidean distance
def euclidean_distance(point1, point2):
    return math.sqrt(sum((p1 - p2) ** 2 for p1, p2 in zip(point1, point2)))

# K-Means Algorithm
for iteration in range(10):  # Max 10 iterations
    clusters = {i: [] for i in range(K)}  # Dictionary to store clusters

    # 2️⃣ Assign Each Point to the Nearest Centroid
    for point in data:
        distances = [euclidean_distance(point, centroid) for centroid in centroids]
        cluster_index = distances.index(min(distances))  # Find the closest centroid
        clusters[cluster_index].append(point)

    # 3️⃣ Update Centroids (Calculate Mean of Each Cluster)
    new_centroids = []
    for i in range(K):
        if clusters[i]:  # Avoid division by zero
            new_x = sum(point[0] for point in clusters[i]) / len(clusters[i])
            new_y = sum(point[1] for point in clusters[i]) / len(clusters[i])
            new_centroids.append([new_x, new_y])
        else:
            new_centroids.append(centroids[i])  # Keep old centroid if cluster is empty

    # Check if centroids have changed
    if new_centroids == centroids:
        break  # Stop if centroids do not change

    centroids = new_centroids  # Update centroids
    print(f"Iteration {iteration + 1}: Updated Centroids: {centroids}")

# 4️⃣ Visualizing the Results
colors = ['r', 'b']
for i in range(K):
    cluster_points = clusters[i]
    if cluster_points:
        x_values = [p[0] for p in cluster_points]
        y_values = [p[1] for p in cluster_points]
        plt.scatter(x_values, y_values, color=colors[i], label=f'Cluster {i+1}')

centroid_x = [c[0] for c in centroids]
centroid_y = [c[1] for c in centroids]
plt.scatter(centroid_x, centroid_y, color='black', marker='x', s=200, label="Centroids")

plt.legend()
plt.title("K-Means Clustering (Without NumPy)")
plt.show()


