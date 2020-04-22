package ada.spd.startup.Domains;

import java.util.List;

public class ListGetResult {

    private List<GetResult> getResults;




    public ListGetResult() {
    }

    public List<GetResult> getGetResults() {
        return getResults;
    }

    public void setGetResults(List<GetResult> getResults) {
        this.getResults = getResults;
    }


    public void addResult(GetResult getResult) {
        this.getResults.add(getResult);
    }
}
