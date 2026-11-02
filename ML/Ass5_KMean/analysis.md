Assignment: K-Means and Hierarchical Clustering
1. Aim
To perform K-Means Clustering and Hierarchical Clustering on a sales dataset and group similar sales records into clusters.

This is an example of Unsupervised Learning, where the model identifies groups without predefined output labels.
2. Explanation of the code
Block 1: Import library
import pandas as pd



- Imports Pandas for reading and handling dataset files.
- pd is the alias used for Pandas.
Block 2: Read the dataset
ds = pd.read_csv("sales_data_sample.csv", encoding="Latin-1")print(ds.head())



- Reads the sales dataset into a DataFrame named ds.
- encoding="Latin-1" helps read special characters in the file.
- head() displays the first five rows.
Block 3: Select features and normalize data
from sklearn.preprocessing import StandardScalerX = ds[['QUANTITYORDERED', 'PRICEEACH', 'SALES', 'MSRP']]scaler = StandardScaler()X_scaled = scaler.fit_transform(X)



- Selects four columns for clustering:
  - QUANTITYORDERED: Quantity of products ordered.
  - PRICEEACH: Price per product.
  - SALES: Sales amount.
  - MSRP: Manufacturer's suggested retail price.
- StandardScaler() standardizes the selected features so they have comparable scales.
- fit_transform() calculates scaling values and transforms the data.
Why scaling? Clustering uses distances. Features with larger numerical values could otherwise have more influence on the results.
Block 4: Determine clusters using Elbow Method
from sklearn.cluster import KMeans, AgglomerativeClusteringimport matplotlib.pyplot as pltwcss = []for i in range(1, 11):    kmeans = KMeans(n_clusters=i, random_state=42, n_init=10)    kmeans.fit(X_scaled)    wcss.append(kmeans.inertia_)plt.plot(range(1, 11), wcss, marker='o')plt.xlabel("Number of Clusters")plt.ylabel("WCSS")plt.title("Elbow Method")plt.grid()plt.show()



- KMeans: Imports the K-Means clustering algorithm.
- AgglomerativeClustering: Imports the hierarchical clustering algorithm.
- wcss: Stores the WCSS value for each cluster count.
- The loop tests cluster counts from 1 to 10.
- n_clusters=i: Number of clusters being tested.
- random_state=42: Makes results reproducible.
- n_init=10: Runs K-Means with 10 different initializations.
- inertia_: Returns WCSS, the sum of squared distances between data points and their assigned cluster centers.
Elbow Method: We plot the number of clusters against WCSS. We choose the point where the decrease in WCSS starts slowing down noticeably. This point looks like an elbow.
Block 5: Apply K-Means Clustering
kmeans = KMeans(n_clusters=3, random_state=42, n_init=10)ds['KMeans_Cluster'] = kmeans.fit_predict(X_scaled)print(ds[['SALES', 'QUANTITYORDERED', 'KMeans_Cluster']].head())



- Creates a K-Means model with 3 clusters.
- fit_predict() trains the model and assigns each record to a cluster.
- The assigned cluster number is stored in a new column called KMeans_Cluster.
- The print() statement displays the first five records with their sales, quantity and cluster number.
Block 6: Apply Hierarchical Clustering
hc = AgglomerativeClustering(n_clusters=3)ds['Hierarchical_Cluster'] = hc.fit_predict(X_scaled)print(ds[['SALES', 'QUANTITYORDERED', 'Hierarchical_Cluster']].head())



- Creates an Agglomerative Hierarchical Clustering model with 3 clusters.
- fit_predict() groups similar records and assigns cluster labels.
- The results are stored in Hierarchical_Cluster.
How it works: It starts with individual data points and gradually merges the closest groups until the required number of clusters remains.
Block 7: Count cluster records
print("K-Means Clusters:")print(ds['KMeans_Cluster'].value_counts())print("\nHierarchical Clusters:")print(ds['Hierarchical_Cluster'].value_counts())



- value_counts() counts how many records belong to each cluster.
- It displays the cluster distribution for both algorithms.
- This helps compare how the two methods grouped the sales records.
3. Difference between K-Means and Hierarchical Clustering
K-Means	Hierarchical
Divides data into a fixed number of clusters.	Builds a hierarchy of clusters.
Uses cluster centroids.	Merges or splits groups based on distance.
Requires the number of clusters in advance.	Can be explored at different cluster counts.
Generally faster for large datasets.	Can be computationally expensive for large datasets.

4. Important viva questions
Q1. What is clustering?

Clustering is an unsupervised learning technique that groups similar data points together.

Q2. What is unsupervised learning?

It is a type of machine learning where data does not have predefined output labels.

Q3. What is K-Means Clustering?

It is an algorithm that divides data into K clusters based on similarity and distance from cluster centers.

Q4. What is a centroid?

A centroid is the center point of a cluster, calculated as the mean of its data points.

Q5. What is the Elbow Method?

It is a technique used to select a suitable number of clusters by observing the WCSS graph.

Q6. What is WCSS?

WCSS stands for Within-Cluster Sum of Squares. It measures how close data points are to their assigned cluster centers.

Q7. What is Hierarchical Clustering?

It is a clustering technique that creates groups by gradually merging or splitting clusters.

Q8. Why do we use StandardScaler?

To bring features to a comparable scale so that large-valued features do not dominate distance calculations.

Q9. What is the difference between fit() and fit_predict()?
- fit() trains the model.
- fit_predict() trains the model and returns cluster labels.
5. Algorithm workflow
1. Import required libraries.
2. Load the sales dataset.
3. Select relevant numerical features.
4. Standardize the data.
5. Use the Elbow Method to examine the number of clusters.
6. Apply K-Means with 3 clusters.
7. Apply Hierarchical Clustering with 3 clusters.
8. Count and compare the cluster assignments.
6. Conclusion
In this assignment, K-Means and Hierarchical Clustering are applied to sales data. Both algorithms group similar sales records into three clusters. The cluster counts help us understand and compare the grouping produced by each algorithm.

Note: The notebook contains code for displaying the results, but no saved numerical output is available here.