import java.util.List;

public class BellmanFordAlgorithm {
	private Graph<String> mGraph; //the graph
	private Node<String> startingPoint; //the starting node
	private Node<String> goalPoint; //the goal node
	
	//constructor
	public BellmanFordAlgorithm(Graph<String> mGraph, Node<String> startingPoint, Node<String> goalPoint) {
		this.mGraph = mGraph;
		this.startingPoint = startingPoint;
		this.goalPoint = goalPoint;
	}
	
	//Bellman Ford Algorithm method
	public int performBellmanFord() {
		mGraph.getNode(startingPoint.vertex()).setDistance(0); //set the starting point's distance to 0
		
		//set all the other nodes' distances to infinity
		for(int i=0;i<mGraph.vertexCount();i++) {
			if(!mGraph.getNode(Integer.toString(i+1)).equals(startingPoint)) {
				mGraph.getNode(Integer.toString(i+1)).setDistance(2147483647);
			}
		}		
		
		for(int i=0;i<mGraph.vertexCount()-1;i++) { //Iteration equal to the number of verices - 1
			//for all the edges
			for(int j=0;j<mGraph.vertexCount();j++) {
				List<Edge<String>> nodeEdges = mGraph.getNode(Integer.toString(j+1)).edges();
				for(Edge<String> edge : nodeEdges) {
					//if distance of target node is greater than distance of current node + weight of the edge between them
					if(edge.toNode().getDistance() > ((edge.fromNode().getDistance() == Integer.MAX_VALUE) ? 
							edge.fromNode().getDistance() : edge.fromNode().getDistance() + edge.getWeight())) {
						//set the distance of the target node equal to this new value
						edge.toNode().setDistance(((edge.fromNode().getDistance() == Integer.MAX_VALUE) ? 
								edge.fromNode().getDistance() : edge.fromNode().getDistance() + edge.getWeight()));
					}
				}
			}			
		}
		return goalPoint.getDistance(); //return the cost of the cheapest path
	}
}
