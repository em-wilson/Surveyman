/* 
 * Ensures consistent display of entity lists site-wide
 */


$(document).ready(function() {
    $("#entityList").each(function(){
        $this = $(this);
        $surveyItems = $this.children();

        $surveyItems.each(function(){
            $survey = $(this);

            $survey.find("a.delete").click(function() {
                location.href = $(this).attr('href');
            });

            $survey.find("a.edit").click(function() {
                location.href = $(this).attr('href');
            });

            $survey.click(function() {
                $viewLink = $(this).find("a.view");
                location.href = $viewLink.attr('href');
            });
        });
    });
});