package visnode.application.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import visnode.application.NodeNetwork;
import visnode.executor.ProcessNode;

/**
 * Node network parser
 */
public class NodeNetworkParser {

    /** Parser */
    private final Gson gson;

    public NodeNetworkParser() {
        this.gson = new GsonBuilder().
                create();
    }
    
    /**
     * Parser to JSON
     *
     * @param network
     * @return String
     */
    public String toJson(NodeNetwork network) {
        Map<String, Object> data = new HashMap<>();
        
        List<Map> nodes = new ArrayList<>();
        network.getNodes().stream().filter((n) -> {
            return n.getDecorated() instanceof ProcessNode;
        }).forEach((node) -> {
            Map<String, Object> param = new HashMap<>();
            ProcessNode processNode = (ProcessNode) node.getDecorated();
            List input = node.getInputParameters().stream().filter((p) -> {
                return processNode.getConnector().getConnection(p.getName()) == null;
            }).map((t) -> {
                Map<String, Object> inputParam = new HashMap<>();
                inputParam.put("parameterName", t.getName());
                inputParam.put("parameterType", t.getType().getName());
                inputParam.put("value", node.getInput(t.getName()));

                return inputParam;
            }).collect(Collectors.toList());
            param.put("position", node.getPosition());
            param.put("processType", processNode.getProcessType().getName());
            param.put("input", input);
            nodes.add(param);
        });
        data.put("nodes", nodes);
        return gson.toJson(data);
    }

    /**
     * Parser from JSON
     *
     * @param json
     * @return NodeNetwork
     */
    public NodeNetwork fromJson(String json) {
        return new NodeNetwork();
    }

}